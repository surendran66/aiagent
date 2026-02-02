package com.surendran.jsontojsontaransformsurendran.controller;

import com.surendran.jsontojsontaransformsurendran.service.DataTransferService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class TransferControllerTest {
    @Mock
    private DataTransferService dataTransferService;
    @InjectMocks
    private TransferController transferController;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void transferStart_ReturnsOk() {
        when(dataTransferService.transferData()).thenReturn(5);
        ResponseEntity<?> response = transferController.transferStart();
        assertEquals(200, response.getStatusCodeValue());
        assertEquals("Transferred 5 records.", response.getBody());
    }
}
