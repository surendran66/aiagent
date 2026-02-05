package com.example.datatojsontransformation.controller;

import com.example.datatojsontransformation.service.DataTransferService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/data-transfer")
public class DataTransferController {
    private final DataTransferService dataTransferService;

    public DataTransferController(DataTransferService dataTransferService) {
        this.dataTransferService = dataTransferService;
    }

    @PostMapping("/transfer")
    public void transferData() {
        dataTransferService.transferData();
    }
}
