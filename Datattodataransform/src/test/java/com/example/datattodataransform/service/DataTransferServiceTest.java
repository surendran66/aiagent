package com.example.datattodataransform.service;

import com.example.datattodataransform.model.InputRecord;
import com.example.datattodataransform.model.OutputRecord;
import com.example.datattodataransform.repository.InputRecordRepository;
import com.example.datattodataransform.repository.OutputRecordRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.time.OffsetDateTime;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class DataTransferServiceTest {
    InputRecordRepository inputRepo;
    OutputRecordRepository outputRepo;
    DataTransferService service;

    @BeforeEach
    void setup() {
        inputRepo = mock(InputRecordRepository.class);
        outputRepo = mock(OutputRecordRepository.class);
        service = new DataTransferService(inputRepo, outputRepo);
    }

    @Test
    void testTransferAndTransform() {
        InputRecord input = new InputRecord("1001", "sysX", "FAILED", "testuser",
                "2024-06-07T10:00:00+00:00", "2024-06-07T10:20:00+00:00");

        when(inputRepo.findAll()).thenReturn(Collections.singletonList(input));
        when(outputRepo.saveAll(any())).thenReturn(Collections.emptyList());

        int count = service.transferAndTransform();
        assertEquals(1, count);
    }
}
