package com.datattodataransform.controller;

import com.datattodataransform.service.DataTransferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;

@RestController
@RequestMapping("/transfer")
public class TransferController {
    @Autowired
    private DataTransferService dataTransferService;

    @PostMapping("/start")
    public ResponseEntity<String> startTransfer() {
        int count = dataTransferService.transfer();
        return ResponseEntity.ok("Transferred records: " + count);
    }
}
