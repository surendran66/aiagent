package com.example.cosmostransfer.service;

import com.example.cosmostransfer.model.InputRecord;
import com.example.cosmostransfer.model.OutputRecord;
import com.example.cosmostransfer.repository.InputRecordRepository;
import com.example.cosmostransfer.repository.OutputRecordRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.time.OffsetDateTime;
import java.util.Arrays;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class DataTransferServiceTest {
    @Mock
    InputRecordRepository inputRepository;
    @Mock
    OutputRecordRepository outputRepository;
    @InjectMocks
    DataTransferService dataTransferService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testTransfer_TransformsCorrectly() {
        InputRecord rec = new InputRecord();
        rec.setId("abc");
        rec.setSystem("CRM");
        rec.setStatus("FAILED");
        rec.setReceivedTime(OffsetDateTime.now().minusMinutes(20));
        rec.setCompletedTime(OffsetDateTime.now());
        rec.setUser("user1");
        when(inputRepository.findAll()).thenReturn(Arrays.asList(rec));
        when(outputRepository.saveAll(any())).thenAnswer(invoc -> invoc.getArgument(0));
        List<OutputRecord> res = dataTransferService.transfer();
        assertEquals(1, res.size());
        OutputRecord out = res.get(0);
        assertEquals(rec.getId(), out.getRecordId());
        assertEquals(rec.getSystem(), out.getService());
        assertEquals(rec.getStatus(), out.getSla().getState());
        assertEquals(rec.getReceivedTime(), out.getReceivedTime());
        assertEquals(rec.getCompletedTime(), out.getCompletedTime());
        assertTrue(out.getDurationMinutes() >= 19); // Due to potential millis diff
        assertTrue(out.isBreached());
        assertEquals(rec.getUser(), out.getOwner());
    }
}
