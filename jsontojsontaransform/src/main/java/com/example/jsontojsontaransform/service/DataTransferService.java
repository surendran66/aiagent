package com.example.jsontojsontaransform.service;

import com.example.jsontojsontaransform.model.InputRecord;
import com.example.jsontojsontaransform.model.OutputRecord;
import com.example.jsontojsontaransform.repository.InputRecordRepository;
import com.example.jsontojsontaransform.repository.OutputRecordRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
@RequiredArgsConstructor
public class DataTransferService {
    private final InputRecordRepository inputRepository;
    private final OutputRecordRepository outputRepository;
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ISO_LOCAL_DATE_TIME;

    public long transfer() {
        Iterable<InputRecord> allInputs = inputRepository.findAll();
        long count = 0;
        for (InputRecord input : allInputs) {
            OutputRecord output = transform(input);
            outputRepository.save(output);
            count++;
        }
        return count;
    }

    public OutputRecord transform(InputRecord input) {
        OutputRecord or = new OutputRecord();
        or.setId(input.getId());
        or.setRecordId(input.getId());
        or.setService(input.getSystem());
        or.setReceivedTime(input.getReceivedTime());
        or.setCompletedTime(input.getCompletedTime());
        or.getSla().setState(input.getStatus());
        long duration = 0L;
        boolean breached = false;
        try {
            LocalDateTime received = LocalDateTime.parse(input.getReceivedTime(), FORMATTER);
            LocalDateTime completed = LocalDateTime.parse(input.getCompletedTime(), FORMATTER);
            duration = Duration.between(received, completed).toMinutes();
        } catch (Exception e) {
            duration = 0L;
        }
        or.setDurationMinutes(duration);
        breached = "FAILED".equalsIgnoreCase(input.getStatus()) || duration > 10L;
        or.setBreached(breached);
        or.setOwner(input.getUser());
        return or;
    }
}
