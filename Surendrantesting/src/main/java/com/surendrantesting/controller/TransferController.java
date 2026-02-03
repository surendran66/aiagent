package com.surendrantesting.controller;
import com.surendrantesting.service.DataTransferService;
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
        dataTransferService.transfer();
        return "Transfer completed";
    }
}
