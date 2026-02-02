package com.example.jsontransform.controller;

import com.example.jsontransform.service.DataTransferService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class TransferController {
    private final DataTransferService transferService;

    @PostMapping("/transfer/start")
    public ResponseEntity<String> startTransfer() {
        int count = transferService.transfer();
        return ResponseEntity.ok("Records transferred: " + count);
    }
}
