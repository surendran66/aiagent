package com.example.service;

import com.example.model.InputRecord;
import com.example.model.OutputRecord;
import com.example.repository.InputRecordRepository;
import com.example.repository.OutputRecordRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.jupiter.api.extension.ExtendWith;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class DataTransferServiceTest {
    @Mock
    InputRecordRepository inputRecordRepository;
    @Mock
    OutputRecordRepository outputRecordRepository;
    @InjectMocks
    DataTransferService dataTransferService;

    @Test
    void testTransferData() {
        InputRecord input = new InputRecord();
        input.setId("abc123");
        input.setSystem("ServiceA");
        input.setStatus("FAILED");
        input.setUser("John Doe");
        input.setReceivedTime("2024-06-01T14:00:00");
        input.setCompletedTime("2024-06-01T14:17:00");
        when(inputRecordRepository.findAll()).thenReturn(Arrays.asList(input));
        int result = dataTransferService.transferData();
        assertEquals(1, result);
        verify(outputRecordRepository, times(1)).saveAll(any());
    }

    @Test
    void testTransformLogic_breachedDuration() {
        InputRecord input = new InputRecord();
        input.setId("id456");
        input.setSystem("XSystem");
        input.setStatus("SUCCESS");
        input.setUser("Jane Doe");
        input.setReceivedTime("2024-06-01T09:15:30");
        input.setCompletedTime("2024-06-01T09:32:30");
        DataTransferService service = new DataTransferService(null, null);
        OutputRecord out = service.transform(input);
        assertEquals("id456", out.getRecordId());
        assertEquals("SUCCESS", out.getSla().getState());
        assertTrue(out.getDurationMinutes() > 10);
        assertTrue(out.isBreached());
    }

    @Test
    void testTransformLogic_notBreached() {
        InputRecord input = new InputRecord();
        input.setId("id789");
        input.setSystem("Sys2");
        input.setStatus("SUCCESS");
        input.setUser("Alan");
        input.setReceivedTime("2024-06-01T10:00:00");
        input.setCompletedTime("2024-06-01T10:07:00");
        DataTransferService service = new DataTransferService(null, null);
        OutputRecord out = service.transform(input);
        assertFalse(out.isBreached());
        assertEquals("Alan", out.getOwner());
        assertEquals("Sys2", out.getService());
    }
}
