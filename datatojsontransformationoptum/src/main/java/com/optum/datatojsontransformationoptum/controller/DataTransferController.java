package com.optum.datatojsontransformationoptum.controller;

import com.optum.datatojsontransformationoptum.model.OutputRecord;
import com.optum.datatojsontransformationoptum.service.DataTransferService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import java.util.List;

@RestController
@RequestMapping("/transfer")
public class DataTransferController {
    private final DataTransferService dataTransferService;

    public DataTransferController(DataTransferService dataTransferService) {
        this.dataTransferService = dataTransferService;
    }

    @PostMapping("/start")
    public List<OutputRecord> startTransfer() {
        return dataTransferService.transferAndTransform();
    }
}
