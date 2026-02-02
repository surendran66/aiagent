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
public class DataTransferController {
    private final DataTransferService dataTransferService;

    @PostMapping("/start")
    public ResponseEntity<String> transferData() {
        int count = dataTransferService.transferData();
        return ResponseEntity.ok("Transferred " + count + " records.");
    }
}
