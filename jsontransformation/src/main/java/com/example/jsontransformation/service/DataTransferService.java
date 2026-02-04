package com.example.jsontransformation.service;

import com.example.jsontransformation.model.InputRecord;
import com.example.jsontransformation.model.OutputRecord;
import com.example.jsontransformation.repository.InputRecordRepository;
import com.example.jsontransformation.repository.OutputRecordRepository;
import org.springframework.stereotype.Service;

@Service
public class DataTransferService {
    private final InputRecordRepository inputRecordRepository;
    private final OutputRecordRepository outputRecordRepository;

    public DataTransferService(InputRecordRepository inputRecordRepository, OutputRecordRepository outputRecordRepository) {
        this.inputRecordRepository = inputRecordRepository;
        this.outputRecordRepository = outputRecordRepository;
    }

    public OutputRecord transform(InputRecord input) {
        OutputRecord.Sla sla = new OutputRecord.Sla(input.getSla());
        return new OutputRecord(
                input.getId(),
                input.getName(),
                input.getType(),
                input.getStatus(),
                input.getPriority(),
                sla
        );
    }

    // Add the missing transferAll method
    public void transferAll() {
        inputRecordRepository.findAll().forEach(input -> {
            OutputRecord output = transform(input);
            outputRecordRepository.save(output);
        });
    }
}
