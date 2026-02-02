package com.suren.jsontojsontaransformsuren.controller;

import com.suren.jsontojsontaransformsuren.service.DataTransferService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/transfer")
@RequiredArgsConstructor
public class TransferController {
    private final DataTransferService dataTransferService;

    @PostMapping("/start")
    public ResponseEntity<String> transfer() {
        int count = dataTransferService.transfer();
        return ResponseEntity.ok("Transferred " + count + " records");
    }
}
