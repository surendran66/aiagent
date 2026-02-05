package com.example.datatojsontransformation.service;

import com.example.datatojsontransformation.model.InputRecord;
import com.example.datatojsontransformation.model.OutputRecord;
import com.example.datatojsontransformation.repository.InputDataRepository;
import com.example.datatojsontransformation.repository.OutputDataRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class DataTransferServiceTest {
    @Test
    void testTransform() {
        InputRecord input = new InputRecord(
                "A123",
                "FAILED",
                "2026-01-26T10:00:00Z",
                "2026-01-26T10:14:30Z",
                "PRINT",
                "John Doe"
        );
        InputDataRepository inputRepo = mock(InputDataRepository.class);
        OutputDataRepository outputRepo = mock(OutputDataRepository.class);
        DataTransferService service = new DataTransferService(inputRepo, outputRepo);
        OutputRecord output = service.transform(input);
        assertEquals("A123", output.getRecordId());
        assertEquals("A123", output.getId());
        assertEquals("sms", output.getService());
        assertEquals("FAILED", output.getSla().getState());
        assertEquals("2026-01-26T10:00:00Z", output.getReceivedTime());
        assertEquals("2026-01-26T10:14:30Z", output.getCompletedTime());
        assertEquals(14.5, output.getDurationMinutes());
        assertTrue(output.isBreached());
        assertEquals("test Doe", output.getOwner());
    }
}
