package com.example.Datattodataransform.controller;

import com.example.Datattodataransform.service.DataTransferService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/transfer")
@RequiredArgsConstructor
public class TransferController {
    private final DataTransferService service;

    @PostMapping("/start")
    public ResponseEntity<String> startTransfer() {
        int count = service.transfer();
        return ResponseEntity.ok("Transferred " + count + " records.");
    }
}
