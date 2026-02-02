package com.example.datattodataransform.controller;

import com.example.datattodataransform.service.DataTransferService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.Assertions.assertThat;

public class TransferControllerTest {
    @Test
    void testStartTransfer() {
        DataTransferService svc = Mockito.mock(DataTransferService.class);
        Mockito.when(svc.transfer()).thenReturn(1L);
        TransferController ctrl = new TransferController(svc);
        ResponseEntity<String> response = ctrl.startTransfer();
        assertThat(response.getBody()).contains("Transferred 1 records.");
    }
}
