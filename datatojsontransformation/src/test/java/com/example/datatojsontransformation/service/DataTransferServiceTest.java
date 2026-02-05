package com.example.datatojsontransformation.service;

import com.example.datatojsontransformation.model.InputRecord;
import com.example.datatojsontransformation.model.OutputRecord;
import com.example.datatojsontransformation.repository.InputRecordRepository;
import com.example.datatojsontransformation.repository.OutputRecordRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class DataTransferServiceTest {
    @Mock
    private InputRecordRepository inputRecordRepository;
    @Mock
    private OutputRecordRepository outputRecordRepository;
    @InjectMocks
    private DataTransferService dataTransferService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testTransferData() {
        InputRecord input = new InputRecord();
        input.setId("1");
        List<InputRecord> inputList = Collections.singletonList(input);
        when(inputRecordRepository.findAll()).thenReturn(inputList);

        // saveAll is not a void method, so we should mock its return value
        when(outputRecordRepository.saveAll(anyList())).thenReturn(Collections.emptyList());

        dataTransferService.transferData();

        verify(inputRecordRepository, times(1)).findAll();
        verify(outputRecordRepository, times(1)).saveAll(anyList());
    }
}
