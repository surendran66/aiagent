package com.example.jsontransform.service;

import com.example.jsontransform.model.InputRecord;
import com.example.jsontransform.model.OutputRecord;
import com.example.jsontransform.repository.InputRecordRepository;
import com.example.jsontransform.repository.OutputRecordRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;

@Service
@RequiredArgsConstructor
public class DataTransferService {
    private final InputRecordRepository inputRecordRepository;
    private final OutputRecordRepository outputRecordRepository;

    public int transfer() {
        int count = 0;
        for (InputRecord input : inputRecordRepository.findAll()) {
            OutputRecord output = transform(input);
            outputRecordRepository.save(output);
            count++;
        }
        return count;
    }

    OutputRecord transform(InputRecord input) {
        String recordId = input.getId();
        String service = input.getSystem();
        OutputRecord.Sla sla = new OutputRecord.Sla(input.getStatus());
        String receivedTime = input.getReceivedTime();
        String completedTime = input.getCompletedTime();
        long durationMinutes = calculateDuration(receivedTime, completedTime);
        boolean breached = "FAILED".equalsIgnoreCase(input.getStatus()) || durationMinutes > 10;
        String owner = input.getUser();
        return new OutputRecord(recordId, input.getId(), service, sla, receivedTime, completedTime, durationMinutes, breached, owner);
    }

    long calculateDuration(String receivedTime, String completedTime) {
        OffsetDateTime start = OffsetDateTime.parse(receivedTime, DateTimeFormatter.ISO_OFFSET_DATE_TIME);
        OffsetDateTime end = OffsetDateTime.parse(completedTime, DateTimeFormatter.ISO_OFFSET_DATE_TIME);
        return Duration.between(start, end).toMinutes();
    }
}
