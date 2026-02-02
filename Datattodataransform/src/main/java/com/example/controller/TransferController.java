package com.example.controller;

import com.example.service.DataTransferService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/transfer")
@RequiredArgsConstructor
public class TransferController {
    private final DataTransferService dataTransferService;

    @PostMapping("/start")
    public String startTransfer() {
        int count = dataTransferService.transferData();
        return String.format("Transferred %d records.", count);
    }
}
