package com.example.datattodataransform.controller;

import com.example.datattodataransform.service.DataTransferService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/transfer")
@RequiredArgsConstructor
public class DataTransferController {
    private final DataTransferService dataTransferService;
    @PostMapping("/start")
    public String transfer() {
        int records = dataTransferService.transfer();
        return String.format("%d records transferred successfully", records);
    }
}
