package com.surendran.jsontojsontaransformsurendran.controller;

import com.surendran.jsontojsontaransformsurendran.service.DataTransferService;
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
    public ResponseEntity<?> transferStart() {
        int transferred = dataTransferService.transferData();
        return ResponseEntity.ok("Transferred " + transferred + " records.");
    }
}
