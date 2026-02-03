package com.example.testingworkflow.service;

import com.example.testingworkflow.model.InputRecord;
import com.example.testingworkflow.model.OutputRecord;
import com.example.testingworkflow.repository.InputRecordRepository;
import com.example.testingworkflow.repository.OutputRecordRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import java.util.Arrays;
import java.util.List;
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
    void testTransfer() {
        InputRecord ir = new InputRecord();
        ir.setId("abc");
        ir.setSystem("testservice");
        ir.setStatus("FAILED");
        ir.setReceivedTime("2024-06-01T10:00:00");
        ir.setCompletedTime("2024-06-01T10:12:00");
        ir.setUser("owner1");
        when(inputRepo.findAll()).thenReturn(Arrays.asList(ir));
        service.transfer();
        verify(outputRepo, times(1)).save(Mockito.any(OutputRecord.class));
    }
}
