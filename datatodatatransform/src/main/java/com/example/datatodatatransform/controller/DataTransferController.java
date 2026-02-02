package com.example.datatodatatransform.controller;

import com.example.datatodatatransform.service.DataTransferService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/transfer")
public class DataTransferController {
    private final DataTransferService service;

    @PostMapping("/start")
    public ResponseEntity<String> transferData() {
        service.transferData();
        return ResponseEntity.ok("Data transfer started and completed.");
    }
}
