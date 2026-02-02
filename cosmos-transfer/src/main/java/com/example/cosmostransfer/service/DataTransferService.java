package com.example.cosmostransfer.service;

import com.example.cosmostransfer.model.InputRecord;
import com.example.cosmostransfer.model.OutputRecord;
import com.example.cosmostransfer.model.Sla;
import com.example.cosmostransfer.repository.InputRecordRepository;
import com.example.cosmostransfer.repository.OutputRecordRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DataTransferService {
    private final InputRecordRepository inputRecordRepository;
    private final OutputRecordRepository outputRecordRepository;

    public int transferData() {
        List<InputRecord> inputs = new ArrayList<>();
        inputRecordRepository.findAll().forEach(inputs::add);
        List<OutputRecord> outputs = inputs.stream().map(this::transform).toList();
        outputRecordRepository.saveAll(outputs);
        return outputs.size();
    }

    OutputRecord transform(InputRecord input) {
        String id = input.getId();
        String system = input.getSystem();
        String status = input.getStatus();
        String receivedTime = input.getReceivedTime();
        String completedTime = input.getCompletedTime();
        String owner = input.getUser();

        long duration = calculateDurationMinutes(receivedTime, completedTime);
        boolean breached = "FAILED".equalsIgnoreCase(status) || duration > 10;
        Sla sla = new Sla(status);

        return new OutputRecord(
                id,
                id,
                system,
                sla,
                receivedTime,
                completedTime,
                duration,
                breached,
                owner
        );
    }

    private long calculateDurationMinutes(String received, String completed) {
        try {
            OffsetDateTime receivedDt = OffsetDateTime.parse(received, DateTimeFormatter.ISO_OFFSET_DATE_TIME);
            OffsetDateTime completedDt = OffsetDateTime.parse(completed, DateTimeFormatter.ISO_OFFSET_DATE_TIME);
            return Duration.between(receivedDt, completedDt).toMinutes();
        } catch (Exception e) {
            return 0L;
        }
    }
}
