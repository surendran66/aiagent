package com.example.datatojsontransformation.service;

import com.example.datatojsontransformation.model.InputRecord;
import com.example.datatojsontransformation.model.OutputRecord;
import com.example.datatojsontransformation.repository.InputRecordRepository;
import com.example.datatojsontransformation.repository.OutputRecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class DataTransferService {
    private final InputRecordRepository inputRecordRepository;
    private final OutputRecordRepository outputRecordRepository;

    @Autowired
    public DataTransferService(InputRecordRepository inputRecordRepository, OutputRecordRepository outputRecordRepository) {
        this.inputRecordRepository = inputRecordRepository;
        this.outputRecordRepository = outputRecordRepository;
    }

    public void transferData() {
        List<InputRecord> inputRecords = StreamSupport.stream(inputRecordRepository.findAll().spliterator(), false)
                .collect(Collectors.toList());
        List<OutputRecord> outputRecords = inputRecords.stream()
                .map(input -> {
                    OutputRecord output = new OutputRecord();
                    output.setId(input.getId());
                    // Add any transformation logic here
                    return output;
                })
                .collect(Collectors.toList());
        outputRecordRepository.saveAll(outputRecords);
    }
}
