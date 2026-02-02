package com.datatransform.controller;

import com.datatransform.service.DataTransferService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class TransferControllerTest {
    @Mock
    private DataTransferService service;
    @InjectMocks
    private TransferController controller;

    public TransferControllerTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testTransferEndpointReturnsCorrectMessage() {
        when(service.transferData()).thenReturn(5);
        ResponseEntity<String> response = controller.transfer();
        assertEquals("Transferred records: 5", response.getBody());
    }
}
