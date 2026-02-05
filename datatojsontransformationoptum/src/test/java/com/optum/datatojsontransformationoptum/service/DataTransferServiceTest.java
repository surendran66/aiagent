package com.optum.datatojsontransformationoptum.service;

import com.optum.datatojsontransformationoptum.model.InputRecord;
import com.optum.datatojsontransformationoptum.model.OutputRecord;
import com.optum.datatojsontransformationoptum.repository.InputDataRepository;
import com.optum.datatojsontransformationoptum.repository.OutputDataRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class DataTransferServiceTest {
    @Test
    void testTransformLogic() {
        InputDataRepository inputRepo = mock(InputDataRepository.class);
        OutputDataRepository outputRepo = mock(OutputDataRepository.class);
        DataTransferService service = new DataTransferService(inputRepo, outputRepo);

        InputRecord input = new InputRecord();
        input.setId("A123");
        input.setStatus("FAILED");
        input.setReceivedTime("2026-01-26T10:00:00Z");
        input.setCompletedTime("2026-01-26T10:14:30Z");
        input.setSystem("PRINT");
        input.setUser("John Doe");

        OutputRecord output = service.transform(input);

        assertEquals("A123", output.getId());
        assertEquals("A123", output.getRecordId());
        assertEquals("sms", output.getService());
        assertEquals("FAILED", output.getSla().getState());
        assertEquals("2026-01-26T10:00:00Z", output.getReceivedTime());
        assertEquals("2026-01-26T10:14:30Z", output.getCompletedTime());
        assertEquals(14.5, output.getDurationMinutes());
        assertTrue(output.isBreached());
        assertEquals("test Doe", output.getOwner());
    }

    @Test
    void testTransferAndTransform() {
        InputDataRepository inputRepo = mock(InputDataRepository.class);
        OutputDataRepository outputRepo = mock(OutputDataRepository.class);
        DataTransferService service = new DataTransferService(inputRepo, outputRepo);

        InputRecord input = new InputRecord();
        input.setId("A123");
        input.setStatus("FAILED");
        input.setReceivedTime("2026-01-26T10:00:00Z");
        input.setCompletedTime("2026-01-26T10:14:30Z");
        input.setSystem("PRINT");
        input.setUser("John Doe");

        when(inputRepo.findAll()).thenReturn(Collections.singletonList(input));
        when(outputRepo.saveAll(anyList())).thenAnswer(invocation -> invocation.getArgument(0));

        List<OutputRecord> outputs = service.transferAndTransform();
        assertEquals(1, outputs.size());
        OutputRecord output = outputs.get(0);
        assertEquals("A123", output.getId());
        assertEquals("A123", output.getRecordId());
        assertEquals("sms", output.getService());
        assertEquals("FAILED", output.getSla().getState());
        assertEquals("2026-01-26T10:00:00Z", output.getReceivedTime());
        assertEquals("2026-01-26T10:14:30Z", output.getCompletedTime());
        assertEquals(14.5, output.getDurationMinutes());
        assertTrue(output.isBreached());
        assertEquals("test Doe", output.getOwner());
    }
}
