package com.example.cosmostransfer.controller;

import com.example.cosmostransfer.model.OutputRecord;
import com.example.cosmostransfer.service.DataTransferService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import java.util.List;

@RestController
@RequestMapping("/transfer")
@RequiredArgsConstructor
public class TransferController {
    private final DataTransferService dataTransferService;

    @PostMapping("/start")
    public List<OutputRecord> transfer() {
        return dataTransferService.transfer();
    }
}
