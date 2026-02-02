package com.firstautomation.controller;

import com.firstautomation.service.DataTransferService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/transfer")
@RequiredArgsConstructor
public class DataTransferController {
    private final DataTransferService service;
    @PostMapping("/start")
    public ResponseEntity<?> startTransfer() {
        service.transferAll();
        return ResponseEntity.ok("Transfer completed");
    }
}
