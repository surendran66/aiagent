package com.example.cosmostransfer.service;

import com.example.cosmostransfer.model.DataItem;
import com.example.cosmostransfer.model.OutputDataItem;
import com.example.cosmostransfer.repository.InputDataRepository;
import com.example.cosmostransfer.repository.OutputDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DataTransferService {
    private final InputDataRepository inputDataRepository;
    private final OutputDataRepository outputDataRepository;

    @Autowired
    public DataTransferService(InputDataRepository inputDataRepository, OutputDataRepository outputDataRepository) {
        this.inputDataRepository = inputDataRepository;
        this.outputDataRepository = outputDataRepository;
    }

    public int transferAllData() {
        List<DataItem> allItems = (List<DataItem>) inputDataRepository.findAll();
        List<OutputDataItem> outputItems = allItems.stream()
                .map(item -> new OutputDataItem(item.getId(), item.getPartitionKey(), item.getData()))
                .collect(Collectors.toList());
        outputDataRepository.saveAll(outputItems);
        return outputItems.size();
    }
}
