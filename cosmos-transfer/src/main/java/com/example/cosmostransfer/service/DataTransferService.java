package com.example.cosmostransfer.service;

import com.example.cosmostransfer.model.InputRecord;
import com.example.cosmostransfer.model.OutputRecord;
import com.example.cosmostransfer.repository.InputRecordRepository;
import com.example.cosmostransfer.repository.OutputRecordRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.stream.StreamSupport;
import java.util.List;
import java.util.ArrayList;
import java.time.Duration;

@Service
@RequiredArgsConstructor
public class DataTransferService {
    private final InputRecordRepository inputRepository;
    private final OutputRecordRepository outputRepository;

    @Transactional
    public List<OutputRecord> transfer() {
        Iterable<InputRecord> inputRecords = inputRepository.findAll();
        List<OutputRecord> outputRecords = new ArrayList<>();
        for (InputRecord in : inputRecords) {
            long durationMinutes = 0;
            if (in.getReceivedTime() != null && in.getCompletedTime() != null) {
                durationMinutes = Duration.between(in.getReceivedTime(), in.getCompletedTime()).toMinutes();
            }
            boolean breached = "FAILED".equalsIgnoreCase(in.getStatus()) || durationMinutes > 10;
            OutputRecord out = new OutputRecord();
            out.setRecordId(in.getId());
            out.setId(in.getId());
            out.setService(in.getSystem());
            out.setSla(new OutputRecord.Sla(in.getStatus()));
            out.setReceivedTime(in.getReceivedTime());
            out.setCompletedTime(in.getCompletedTime());
            out.setDurationMinutes(durationMinutes);
            out.setBreached(breached);
            out.setOwner(in.getUser());
            outputRecords.add(out);
        }
        outputRepository.saveAll(outputRecords);
        return outputRecords;
    }
}
