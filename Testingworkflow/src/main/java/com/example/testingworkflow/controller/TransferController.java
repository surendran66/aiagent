package com.example.testingworkflow.controller;

import com.example.testingworkflow.service.DataTransferService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/transfer")
@RequiredArgsConstructor
public class TransferController {

    private final DataTransferService service;

    @PostMapping("/start")
    public String startTransfer() {
        service.transfer();
        return "Transfer Started";
    }
}
