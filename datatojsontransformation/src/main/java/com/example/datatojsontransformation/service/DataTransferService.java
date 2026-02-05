package com.example.datatojsontransformation.service;

import com.example.datatojsontransformation.model.InputRecord;
import com.example.datatojsontransformation.model.OutputRecord;
import com.example.datatojsontransformation.repository.InputDataRepository;
import com.example.datatojsontransformation.repository.OutputDataRepository;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class DataTransferService {
    private final InputDataRepository inputDataRepository;
    private final OutputDataRepository outputDataRepository;

    public DataTransferService(InputDataRepository inputDataRepository, OutputDataRepository outputDataRepository) {
        this.inputDataRepository = inputDataRepository;
        this.outputDataRepository = outputDataRepository;
    }

    public List<OutputRecord> transferAndTransform() {
        Iterable<InputRecord> inputIterable = inputDataRepository.findAll();
        List<InputRecord> inputRecords = StreamSupport.stream(inputIterable.spliterator(), false)
                .collect(Collectors.toList());
        List<OutputRecord> outputRecords = inputRecords.stream()
                .map(this::transform)
                .collect(Collectors.toList());
        outputDataRepository.saveAll(outputRecords);
        return outputRecords;
    }

    public OutputRecord transform(InputRecord input) {
        String id = input.getId();
        String recordId = id;
        String service = "PRINT".equalsIgnoreCase(input.getSystem()) ? "sms" : input.getSystem();
        OutputRecord.Sla sla = new OutputRecord.Sla(input.getStatus());
        String receivedTime = input.getReceivedTime();
        String completedTime = input.getCompletedTime();
        double durationMinutes = calculateDurationMinutes(receivedTime, completedTime);
        boolean breached = "FAILED".equalsIgnoreCase(input.getStatus()) || durationMinutes > 10.0;
        String owner = extractOwner(input.getUser());
        return new OutputRecord(id, recordId, service, sla, receivedTime, completedTime, durationMinutes, breached, owner);
    }

    private double calculateDurationMinutes(String receivedTime, String completedTime) {
        Instant received = Instant.parse(receivedTime);
        Instant completed = Instant.parse(completedTime);
        Duration duration = Duration.between(received, completed);
        double minutes = duration.getSeconds() / 60.0;
        return Math.round(minutes * 10.0) / 10.0;
    }

    private String extractOwner(String user) {
        if (user == null || user.isEmpty()) return "test";
        String[] parts = user.split(" ");
        String lastName = parts.length > 1 ? parts[parts.length - 1] : parts[0];
        return "test " + lastName;
    }
}
