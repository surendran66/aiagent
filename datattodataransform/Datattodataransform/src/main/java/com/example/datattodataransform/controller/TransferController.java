package com.example.datattodataransform.controller;

import com.example.datattodataransform.service.DataTransferService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/transfer")
@RequiredArgsConstructor
public class TransferController {

    private final DataTransferService transferService;

    @PostMapping("/start")
    public ResponseEntity<?> startTransfer() {
        int count = transferService.transfer();
        return ResponseEntity.ok("Transferred: " + count);
    }
}
