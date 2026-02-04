package com.example.datatransformation.controller;

import com.example.datatransformation.model.Outputdata;
import com.example.datatransformation.service.DataTransferService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import java.util.List;

@RestController
@RequestMapping("/transfer")
public class DataTransferController {
    private final DataTransferService dataTransferService;

    public DataTransferController(DataTransferService dataTransferService) {
        this.dataTransferService = dataTransferService;
    }

    @PostMapping("/start")
    public List<Outputdata> startTransfer() {
        return dataTransferService.transferAndTransform();
    }
}
