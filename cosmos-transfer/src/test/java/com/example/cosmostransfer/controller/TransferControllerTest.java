package com.example.cosmostransfer.controller;

import com.example.cosmostransfer.model.OutputRecord;
import com.example.cosmostransfer.service.DataTransferService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.util.Collections;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class TransferControllerTest {
    @Mock
    DataTransferService dataTransferService;
    @InjectMocks
    TransferController controller;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testTransferEndpointCallsService() {
        OutputRecord or = new OutputRecord();
        when(dataTransferService.transfer()).thenReturn(Collections.singletonList(or));
        List<OutputRecord> result = controller.transfer();
        assertEquals(1, result.size());
        verify(dataTransferService, times(1)).transfer();
    }
}
