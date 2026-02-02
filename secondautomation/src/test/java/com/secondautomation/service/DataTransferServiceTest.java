package com.secondautomation.service;

import com.secondautomation.model.InputRecord;
import com.secondautomation.model.OutputRecord;
import com.secondautomation.repository.InputRecordRepository;
import com.secondautomation.repository.OutputRecordRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.util.Collections;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class DataTransferServiceTest {
    @Mock
    private InputRecordRepository inputRepo;
    @Mock
    private OutputRecordRepository outputRepo;
    @InjectMocks
    private DataTransferService service;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testTransferAndTransformData() {
        InputRecord input = new InputRecord("id123", "CRM", "FAILED", "UserA", "2024-06-01T10:00:00Z", "2024-06-01T10:15:00Z");
        when(inputRepo.findAll()).thenReturn(Collections.singleton(input));

        service.transferAndTransformData();
        ArgumentCaptor<OutputRecord> captor = ArgumentCaptor.forClass(OutputRecord.class);
        verify(outputRepo).save(captor.capture());
        OutputRecord out = captor.getValue();
        assertEquals("id123", out.getRecordId());
        assertEquals("id123", out.getId());
        assertEquals("CRM", out.getService());
        assertEquals("FAILED", out.getSla().getState());
        assertEquals("2024-06-01T10:00:00Z", out.getReceivedTime());
        assertEquals("2024-06-01T10:15:00Z", out.getCompletedTime());
        assertEquals(15, out.getDurationMinutes());
        assertTrue(out.isBreached());
        assertEquals("UserA", out.getOwner());
    }

    @Test
    void testCalculateDurationMinutesHandlesParseError() {
        InputRecord input = new InputRecord("id123", "CRM", "COMPLETED", "UserA", "notADate", "notADate");
        when(inputRepo.findAll()).thenReturn(Collections.singleton(input));
        service.transferAndTransformData();
        ArgumentCaptor<OutputRecord> captor = ArgumentCaptor.forClass(OutputRecord.class);
        verify(outputRepo).save(captor.capture());
        OutputRecord out = captor.getValue();
        assertEquals(0L, out.getDurationMinutes());
        assertFalse(out.isBreached());
    }
}