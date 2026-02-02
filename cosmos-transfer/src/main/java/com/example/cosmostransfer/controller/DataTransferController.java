package com.example.cosmostransfer.controller;

import com.example.cosmostransfer.service.DataTransferService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/transfer")
@RequiredArgsConstructor
public class DataTransferController {
    private final DataTransferService dataTransferService;

    @PostMapping("/start")
    public ResponseEntity<?> startTransfer() {
        int count = dataTransferService.transferData();
        return ResponseEntity.ok(Map.of("transferredRecords", count));
    }
}
