package com.example.jsontransformation.controller;

import com.example.jsontransformation.model.InputRecord;
import com.example.jsontransformation.model.OutputRecord;
import com.example.jsontransformation.service.DataTransferService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
public class DataTransferController {
    private final DataTransferService dataTransferService;

    public DataTransferController(DataTransferService dataTransferService) {
        this.dataTransferService = dataTransferService;
    }

    @PostMapping("/transfer")
    public List<OutputRecord> transferAndTransform(@RequestBody List<InputRecord> inputRecords) {
        return dataTransferService.transferAndTransform(inputRecords);
    }
}
