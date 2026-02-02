package com.datatransform.controller;

import com.datatransform.service.DataTransferService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class TransferController {
    private final DataTransferService service;

    @PostMapping("/transfer/start")
    public ResponseEntity<String> transfer() {
        int count = service.transferData();
        return ResponseEntity.ok("Transferred records: " + count);
    }
}
