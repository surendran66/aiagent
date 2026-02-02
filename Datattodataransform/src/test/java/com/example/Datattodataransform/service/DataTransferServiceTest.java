package com.example.Datattodataransform.service;

import com.example.Datattodataransform.model.InputRecord;
import com.example.Datattodataransform.model.OutputRecord;
import com.example.Datattodataransform.repository.InputRecordRepository;
import com.example.Datattodataransform.repository.OutputRecordRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class DataTransferServiceTest {

    @Mock
    private InputRecordRepository inputRepository;
    @Mock
    private OutputRecordRepository outputRepository;

    @InjectMocks
    private DataTransferService dataTransferService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testTransfer() {
        InputRecord in1 = new InputRecord("1", "sysA", "COMPLETED", "user1", "2024-01-01T10:00:00", "2024-01-01T10:05:00");
        InputRecord in2 = new InputRecord("2", "sysB", "FAILED", "user2", "2024-01-01T11:00:00", "2024-01-01T11:20:00");
        List<InputRecord> records = Arrays.asList(in1, in2);
        when(inputRepository.findAll()).thenReturn(records);
        when(outputRepository.saveAll(any())).thenAnswer(i -> i.getArguments()[0]);

        int count = dataTransferService.transfer();
        assertEquals(2, count);
    }

    @Test
    void testDurationAndBreach() {
        InputRecord in = new InputRecord("3", "sysC", "RUNNING", "user3", "2024-01-01T09:00:00", "2024-01-01T09:12:00");
        OutputRecord out = DataTransferService.transformRecord(in);
        assertEquals(12, out.getDurationMinutes());
        assertTrue(out.isBreached());
    }
}
