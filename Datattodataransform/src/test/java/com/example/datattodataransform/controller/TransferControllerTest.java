package com.example.datattodataransform.controller;

import com.example.datattodataransform.service.DataTransferService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(TransferController.class)
class TransferControllerTest {
    @Autowired
    MockMvc mockMvc;

    @MockBean
    DataTransferService dataTransferService;

    @Test
    void startTransferShouldReturnCount() throws Exception {
        when(dataTransferService.transferAndTransform()).thenReturn(3);
        mockMvc.perform(post("/transfer/start").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.recordsTransferred").value(3));
    }
}
