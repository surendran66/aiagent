package com.example.datatransformation.service;

import com.example.datatransformation.model.Inputdata;
import com.example.datatransformation.model.Outputdata;
import com.example.datatransformation.repository.InputRecordRepository;
import com.example.datatransformation.repository.OutputRecordRepository;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class DataTransferService {
    private final InputRecordRepository inputRecordRepository;
    private final OutputRecordRepository outputRecordRepository;

    public DataTransferService(InputRecordRepository inputRecordRepository, OutputRecordRepository outputRecordRepository) {
        this.inputRecordRepository = inputRecordRepository;
        this.outputRecordRepository = outputRecordRepository;
    }

    public List<Outputdata> transferAndTransform() {
        Iterable<Inputdata> inputIterable = inputRecordRepository.findAll();
        List<Inputdata> inputRecords = StreamSupport.stream(inputIterable.spliterator(), false).collect(Collectors.toList());
        List<Outputdata> outputRecords = inputRecords.stream().map(this::transform).collect(Collectors.toList());
        outputRecordRepository.saveAll(outputRecords);
        return outputRecords;
    }

    public Outputdata transform(Inputdata input) {
        String recordId = input.getId();
        String id = input.getId();
        String service = "PRINT".equalsIgnoreCase(input.getSystem()) ? "sms" : input.getSystem();
        Outputdata.Sla sla = new Outputdata.Sla(input.getStatus());
        String receivedTime = input.getReceivedTime();
        String completedTime = input.getCompletedTime();
        double durationMinutes = calculateDurationMinutes(receivedTime, completedTime);
        boolean breached = "FAILED".equalsIgnoreCase(input.getStatus()) || durationMinutes > 10.0;
        String owner = "suren " + extractLastName(input.getUser());
        return new Outputdata(recordId, id, service, sla, receivedTime, completedTime, durationMinutes, breached, owner);
    }

    private double calculateDurationMinutes(String receivedTime, String completedTime) {
        Instant received = Instant.parse(receivedTime);
        Instant completed = Instant.parse(completedTime);
        Duration duration = Duration.between(received, completed);
        long seconds = duration.getSeconds();
        return seconds / 60.0;
    }

    private String extractLastName(String user) {
        if (user == null || user.isEmpty()) return "";
        String[] parts = user.trim().split("\\s+");
        return parts.length > 1 ? parts[parts.length - 1] : parts[0];
    }
}
