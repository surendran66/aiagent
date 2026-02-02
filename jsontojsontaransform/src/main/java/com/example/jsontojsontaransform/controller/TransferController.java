package com.example.jsontojsontaransform.controller;

import com.example.jsontojsontaransform.service.DataTransferService;
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
    public ResponseEntity<String> startTransfer() {
        long count = transferService.transfer();
        return ResponseEntity.ok("Transferred: " + count);
    }
}
