package com.example.cosmostransfer.controller;

import com.example.cosmostransfer.service.DataTransferService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.ResponseEntity;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DataTransferControllerTest {
    @Test
    void transferAllData_shouldReturnCorrectResponse() {
        DataTransferService service = Mockito.mock(DataTransferService.class);
        Mockito.when(service.transferAllData()).thenReturn(3);

        DataTransferController controller = new DataTransferController(service);
        ResponseEntity<Map<String, Object>> response = controller.transferAllData();

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(3, response.getBody().get("recordsTransferred"));
    }
}
