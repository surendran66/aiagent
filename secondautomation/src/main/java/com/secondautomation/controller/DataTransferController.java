package com.secondautomation.controller;

import com.secondautomation.service.DataTransferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/transfer")
public class DataTransferController {
    private final DataTransferService service;

    @Autowired
    public DataTransferController(DataTransferService service) {
        this.service = service;
    }

    @PostMapping("/start")
    public ResponseEntity<String> startTransfer() {
        service.transferAndTransformData();
        return ResponseEntity.ok("Data transfer started and completed.");
    }
}
