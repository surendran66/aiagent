package com.example.jsontojsontaransform.service;

import com.example.jsontojsontaransform.model.InputRecord;
import com.example.jsontojsontaransform.model.OutputRecord;
import com.example.jsontojsontaransform.repository.InputRecordRepository;
import com.example.jsontojsontaransform.repository.OutputRecordRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class DataTransferServiceTest {
    private InputRecordRepository inputRepo;
    private OutputRecordRepository outputRepo;
    private DataTransferService service;

    @BeforeEach
    void setUp() {
        inputRepo = mock(InputRecordRepository.class);
        outputRepo = mock(OutputRecordRepository.class);
        service = new DataTransferService(inputRepo, outputRepo);
    }

    @Test
    void testTransferAndTransform() {
        InputRecord input = new InputRecord();
        input.setId("1");
        input.setSystem("CRM");
        input.setStatus("FAILED");
        input.setReceivedTime("2023-05-01T10:00:00Z");
        input.setCompletedTime("2023-05-01T10:15:00Z");
        input.setUser("userA");
        when(inputRepo.findAll()).thenReturn(Collections.singleton(input));
        when(outputRepo.save(any())).thenReturn(null);
        long count = service.transferAndTransform();
        assertEquals(1, count);
        ArgumentCaptor<OutputRecord> cap = ArgumentCaptor.forClass(OutputRecord.class);
        verify(outputRepo).save(cap.capture());
        OutputRecord out = cap.getValue();
        assertEquals("1", out.getRecordId());
        assertEquals("1", out.getId());
        assertEquals("CRM", out.getService());
        assertNotNull(out.getSla());
        assertEquals("FAILED", out.getSla().getState());
        assertEquals("2023-05-01T10:00:00Z", out.getReceivedTime());
        assertEquals("2023-05-01T10:15:00Z", out.getCompletedTime());
        assertEquals(15, out.getDurationMinutes());
        assertTrue(out.isBreached());
        assertEquals("userA", out.getOwner());
    }
}
