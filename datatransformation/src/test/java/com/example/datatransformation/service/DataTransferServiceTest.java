package com.example.datatransformation.service;

import com.example.datatransformation.model.Inputdata;
import com.example.datatransformation.model.Outputdata;
import com.example.datatransformation.repository.InputDataRepository;
import com.example.datatransformation.repository.OutputDataRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;

class DataTransferServiceTest {
    @Test
    void testTransform() {
        InputDataRepository inputRepo = Mockito.mock(InputDataRepository.class);
        OutputDataRepository outputRepo = Mockito.mock(OutputDataRepository.class);
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
        assertEquals(14.5, output.getDurationMinutes());
        assertTrue(output.isBreached());
        assertEquals("suren Doe", output.getOwner());
    }
}
