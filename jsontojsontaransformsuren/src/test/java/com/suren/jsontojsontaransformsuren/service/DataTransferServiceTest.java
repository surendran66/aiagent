package com.suren.jsontojsontaransformsuren.service;

import com.suren.jsontojsontaransformsuren.model.InputRecord;
import com.suren.jsontojsontaransformsuren.model.OutputRecord;
import com.suren.jsontojsontaransformsuren.repository.InputRecordRepository;
import com.suren.jsontojsontaransformsuren.repository.OutputRecordRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.time.OffsetDateTime;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class DataTransferServiceTest {

    @Test
    public void testTransfer() {
        InputRecordRepository inputRepo = mock(InputRecordRepository.class);
        OutputRecordRepository outputRepo = mock(OutputRecordRepository.class);
        DataTransferService service = new DataTransferService(inputRepo, outputRepo);
        InputRecord record = new InputRecord();
        record.setId("abc123");
        record.setSystem("Orders");
        record.setStatus("FAILED");
        record.setUser("John Doe");
        record.setReceivedTime("2024-06-01T12:00:00Z");
        record.setCompletedTime("2024-06-01T12:15:00Z");
        
        when(inputRepo.findAll()).thenReturn(Collections.singletonList(record));
        when(outputRepo.save(any(OutputRecord.class))).thenReturn(new OutputRecord());

        int count = service.transfer();
        assertEquals(1, count);
        
        verify(inputRepo, times(1)).findAll();
        verify(outputRepo, times(1)).save(any(OutputRecord.class));
    }
}
