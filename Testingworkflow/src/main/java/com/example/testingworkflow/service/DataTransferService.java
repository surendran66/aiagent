package com.example.testingworkflow.service;

import com.example.testingworkflow.model.InputRecord;
import com.example.testingworkflow.model.OutputRecord;
import com.example.testingworkflow.repository.InputRecordRepository;
import com.example.testingworkflow.repository.OutputRecordRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class DataTransferService {
    private final InputRecordRepository inputRepo;
    private final OutputRecordRepository outputRepo;

    public DataTransferService(InputRecordRepository inputRepo, OutputRecordRepository outputRepo) {
        this.inputRepo = inputRepo;
        this.outputRepo = outputRepo;
    }

    public void transfer() {
        List<InputRecord> records = inputRepo.findAll();
        for (InputRecord ir : records) {
            OutputRecord or = new OutputRecord();
            or.setId(ir.getId());
            or.setSystem(ir.getSystem());
            or.setStatus(ir.getStatus());
            or.setReceivedTime(ir.getReceivedTime());
            or.setCompletedTime(ir.getCompletedTime());
            or.setUser(ir.getUser());
            outputRepo.save(or);
        }
    }
}
