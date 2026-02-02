package com.example.datattodataransform.service;

import com.example.datattodataransform.model.InputRecord;
import com.example.datattodataransform.model.OutputRecord;
import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class DataTransferServiceTest {
    @Test
    void testTransformation() {
        DataTransferService.InputRecordRepository inputRepo = mock(DataTransferService.InputRecordRepository.class);
        DataTransferService.OutputRecordRepository outputRepo = mock(DataTransferService.OutputRecordRepository.class);
        DataTransferService service = new DataTransferService(inputRepo, outputRepo);

        InputRecord input = InputRecord.builder()
                .id("123")
                .system("X")
                .status("FAILED")
                .receivedTime("2024-06-10T12:00:00+00:00")
                .completedTime("2024-06-10T12:30:00+00:00")
                .user("user1")
                .build();
        when(inputRepo.findAll()).thenReturn(List.of(input));

        int count = service.transfer();
        assertEquals(1, count);
        verify(outputRepo, times(1)).saveAll(any());
    }
}
