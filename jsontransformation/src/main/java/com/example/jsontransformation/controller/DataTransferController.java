package com.example.jsontransformation.controller;

import com.example.jsontransformation.model.OutputRecord;
import com.example.jsontransformation.service.DataTransferService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequestMapping("/data-transfer")
public class DataTransferController {
    private final DataTransferService dataTransferService;

    public DataTransferController(DataTransferService dataTransferService) {
        this.dataTransferService = dataTransferService;
    }

    @PostMapping("/transfer")
    public void transferAll() {
        dataTransferService.transferAll();
    }
}
