package com.example.datatodatatransform.service;

import com.example.datatodatatransform.model.InputRecord;
import com.example.datatodatatransform.model.OutputRecord;
import com.example.datatodatatransform.repository.InputRecordRepository;
import com.example.datatodatatransform.repository.OutputRecordRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Duration;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;

@Service
@RequiredArgsConstructor
public class DataTransferService {
    private final InputRecordRepository inputRecordRepository;
    private final OutputRecordRepository outputRecordRepository;

    @Transactional
    public void transferData() {
        Iterable<InputRecord> inputRecords = inputRecordRepository.findAll();
        for (InputRecord input : inputRecords) {
            OutputRecord output = transform(input);
            outputRecordRepository.save(output);
        }
    }

    OutputRecord transform(InputRecord input) {
        OutputRecord output = new OutputRecord();
        output.setId(input.getId());
        output.setRecordId(input.getId());
        output.setService(input.getSystem());
        OutputRecord.Sla sla = new OutputRecord.Sla(input.getStatus());
        output.setSla(sla);
        output.setOwner(input.getUser());
        output.setReceivedTime(input.getReceivedTime());
        output.setCompletedTime(input.getCompletedTime());
        long duration = calculateMinutes(input.getReceivedTime(), input.getCompletedTime());
        output.setDurationMinutes(duration);
        boolean breached = "FAILED".equalsIgnoreCase(input.getStatus()) || duration > 10L;
        output.setBreached(breached);
        return output;
    }

    long calculateMinutes(String receivedTime, String completedTime) {
        try {
            OffsetDateTime start = OffsetDateTime.parse(receivedTime, DateTimeFormatter.ISO_OFFSET_DATE_TIME);
            OffsetDateTime end = OffsetDateTime.parse(completedTime, DateTimeFormatter.ISO_OFFSET_DATE_TIME);
            return Duration.between(start, end).toMinutes();
        } catch (Exception ex) {
            return 0L;
        }
    }
}
