package com.example.datattodataransform.service;

import com.example.datattodataransform.model.InputRecord;
import com.example.datattodataransform.model.OutputRecord;
import com.example.datattodataransform.repository.InputRecordRepository;
import com.example.datattodataransform.repository.OutputRecordRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class DataTransferServiceTest {
    @Mock
    InputRecordRepository inputRepo;
    @Mock
    OutputRecordRepository outputRepo;
    @InjectMocks
    DataTransferService transferService;
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testTransferWithTransformation() {
        InputRecord in = new InputRecord();
        in.setId("abc");
        in.setSystem("svc");
        in.setStatus("FAILED");
        in.setReceivedTime("2024-04-25T10:00:00Z");
        in.setCompletedTime("2024-04-25T10:30:00Z");
        in.setUser("u");
        when(inputRepo.findAll()).thenReturn(Collections.singletonList(in));
        ArgumentCaptor<OutputRecord> captor = ArgumentCaptor.forClass(OutputRecord.class);
        when(outputRepo.save(any())).thenAnswer(x -> x.getArgument(0));
        int cnt = transferService.transfer();
        verify(outputRepo, times(1)).save(captor.capture());
        OutputRecord out = captor.getValue();
        assertEquals("abc", out.getRecordId());
        assertEquals("svc", out.getService());
        assertEquals("FAILED", out.getSla().getState());
        assertEquals(30, out.getDurationMinutes());
        assertTrue(out.isBreached());
        assertEquals("u", out.getOwner());
        assertEquals(1, cnt);
    }

    @Test
    void testDurationCalculationAndNonBreach() {
        InputRecord in = new InputRecord();
        in.setId("id2");
        in.setSystem("svc2");
        in.setStatus("SUCCESS");
        in.setReceivedTime("2024-04-25T10:00:00Z");
        in.setCompletedTime("2024-04-25T10:07:00Z");
        in.setUser("owner2");
        when(inputRepo.findAll()).thenReturn(Arrays.asList(in));
        when(outputRepo.save(any())).thenAnswer(x -> x.getArgument(0));
        int cnt = transferService.transfer();
        assertEquals(1, cnt);
    }
}
