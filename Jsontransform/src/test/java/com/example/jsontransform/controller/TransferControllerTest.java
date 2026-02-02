package com.example.jsontransform.controller;

import com.example.jsontransform.service.DataTransferService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

@WebMvcTest(TransferController.class)
public class TransferControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private DataTransferService transferService;

    @Test
    void transferEndpoint() throws Exception {
        when(transferService.transfer()).thenReturn(5);
        mockMvc.perform(post("/transfer/start"))
                .andExpect(status().isOk())
                .andExpect(content().string("Records transferred: 5"));
        verify(transferService).transfer();
    }
}
