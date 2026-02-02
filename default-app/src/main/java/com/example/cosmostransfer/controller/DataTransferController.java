package com.example.cosmostransfer.controller;

import com.example.cosmostransfer.service.DataTransferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/transfer")
public class DataTransferController {
    private final DataTransferService service;

    @Autowired
    public DataTransferController(DataTransferService service) {
        this.service = service;
    }

    @PostMapping("/all")
    public ResponseEntity<Map<String, Object>> transferAllData() {
        int transferred = service.transferAllData();
        Map<String, Object> response = new HashMap<>();
        response.put("recordsTransferred", transferred);
        return ResponseEntity.ok(response);
    }
}
