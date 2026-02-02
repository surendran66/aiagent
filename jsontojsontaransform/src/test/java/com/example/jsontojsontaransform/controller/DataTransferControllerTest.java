package com.example.jsontojsontaransform.controller;

import com.example.jsontojsontaransform.service.DataTransferService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.beans.factory.annotation.Autowired;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.mockito.Mockito.when;

@WebMvcTest(controllers = DataTransferController.class)
class DataTransferControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private DataTransferService service;

    @Test
    void testTransferEndpoint() throws Exception {
        when(service.transferData()).thenReturn(2);
        mockMvc.perform(post("/transfer/start"))
                .andExpect(status().isOk())
                .andExpect(content().string("Transferred 2 records."));
    }
}
