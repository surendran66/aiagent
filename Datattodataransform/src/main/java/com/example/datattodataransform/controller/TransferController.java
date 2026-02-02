package com.example.datattodataransform.controller;

import com.example.datattodataransform.service.DataTransferService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/transfer")
@RequiredArgsConstructor
public class TransferController {
    private final DataTransferService dataTransferService;

    @PostMapping("/start")
    public ResponseEntity<Map<String, Object>> startTransfer() {
        int count = dataTransferService.transferAndTransform();
        Map<String, Object> resp = new HashMap<>();
        resp.put("recordsTransferred", count);
        return ResponseEntity.ok(resp);
    }
}
