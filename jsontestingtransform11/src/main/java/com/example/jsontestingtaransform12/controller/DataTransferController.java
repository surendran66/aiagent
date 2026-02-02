package com.example.jsontestingtaransform12.controller;

import com.example.jsontestingtaransform12.service.DataTransferService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/transfer")
@RequiredArgsConstructor
public class DataTransferController {
    private final DataTransferService dataTransferService;

    @PostMapping("/start")
    public ResponseEntity<String> startTransfer() {
        dataTransferService.transferData();
        return ResponseEntity.ok("Transfer completed");
    }
}
