package com.example.datatransformation.service;

import com.example.datatransformation.model.Inputdata;
import com.example.datatransformation.model.Outputdata;
import com.example.datatransformation.repository.InputRecordRepository;
import com.example.datatransformation.repository.OutputRecordRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class DataTransferServiceTest {
    @Test
    void testTransform() {
        InputRecordRepository inputRepo = mock(InputRecordRepository.class);
        OutputRecordRepository outputRepo = mock(OutputRecordRepository.class);
        DataTransferService service = new DataTransferService(inputRepo, outputRepo);

        Inputdata input = new Inputdata(
                "A123",
                "FAILED",
                "2026-01-26T10:00:00Z",
                "2026-01-26T10:14:30Z",
                "PRINT",
                "John Doe"
        );

        Outputdata output = service.transform(input);

        assertEquals("A123", output.getRecordId());
        assertEquals("A123", output.getId());
        assertEquals("sms", output.getService());
        assertEquals("FAILED", output.getSla().getState());
        assertEquals("2026-01-26T10:00:00Z", output.getReceivedTime());
        assertEquals("2026-01-26T10:14:30Z", output.getCompletedTime());
        assertEquals(14.5, output.getDurationMinutes(), 0.0001);
        assertTrue(output.isBreached());
        assertEquals("suren Doe", output.getOwner());
    }

    @Test
    void testTransferAndTransform() {
        InputRecordRepository inputRepo = mock(InputRecordRepository.class);
        OutputRecordRepository outputRepo = mock(OutputRecordRepository.class);
        DataTransferService service = new DataTransferService(inputRepo, outputRepo);

        Inputdata input = new Inputdata(
                "A123",
                "FAILED",
                "2026-01-26T10:00:00Z",
                "2026-01-26T10:14:30Z",
                "PRINT",
                "John Doe"
        );
        when(inputRepo.findAll()).thenReturn(Collections.singletonList(input));
        when(outputRepo.saveAll(anyList())).thenReturn(Collections.emptyList());

        List<Outputdata> outputs = service.transferAndTransform();
        assertEquals(1, outputs.size());
        Outputdata output = outputs.get(0);
        assertEquals("A123", output.getRecordId());
        assertEquals("sms", output.getService());
        assertEquals(14.5, output.getDurationMinutes(), 0.0001);
        assertTrue(output.isBreached());
        assertEquals("suren Doe", output.getOwner());
    }
}
