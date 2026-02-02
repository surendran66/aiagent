package com.example.service;

import com.example.model.InputRecord;
import com.example.model.OutputRecord;
import com.example.model.SLA;
import com.example.repository.InputRecordRepository;
import com.example.repository.OutputRecordRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DataTransferService {
    private final InputRecordRepository inputRecordRepository;
    private final OutputRecordRepository outputRecordRepository;
    private static final DateTimeFormatter formatter = DateTimeFormatter.ISO_DATE_TIME;

    public int transferData() {
        List<InputRecord> inputs = inputRecordRepository.findAll();
        List<OutputRecord> outputs = inputs.stream().map(this::transform).collect(Collectors.toList());
        outputRecordRepository.saveAll(outputs);
        return outputs.size();
    }

    private OutputRecord transform(InputRecord input) {
        OutputRecord output = new OutputRecord();
        output.setRecordId(input.getId());
        output.setId(input.getId());
        output.setService(input.getSystem());
        SLA sla = new SLA();
        sla.setState(input.getStatus());
        output.setSla(sla);
        output.setReceivedTime(input.getReceivedTime());
        output.setCompletedTime(input.getCompletedTime());
        long durationMinutes = calculateDuration(input.getReceivedTime(), input.getCompletedTime());
        output.setDurationMinutes(durationMinutes);
        output.setBreached("FAILED".equalsIgnoreCase(input.getStatus()) || durationMinutes > 10);
        output.setOwner(input.getUser());
        return output;
    }

    private long calculateDuration(String start, String end) {
        try {
            LocalDateTime s = LocalDateTime.parse(start, formatter);
            LocalDateTime e = LocalDateTime.parse(end, formatter);
            return Duration.between(s, e).toMinutes();
        } catch (Exception ex) {
            return 0L;
        }
    }
}
