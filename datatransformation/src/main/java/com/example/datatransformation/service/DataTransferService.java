package com.example.datatransformation.service;

import com.example.datatransformation.model.Inputdata;
import com.example.datatransformation.model.Outputdata;
import com.example.datatransformation.repository.InputDataRepository;
import com.example.datatransformation.repository.OutputDataRepository;
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

    public List<Outputdata> transferAndTransform() {
        List<Inputdata> inputList = StreamSupport.stream(inputDataRepository.findAll().spliterator(), false)
                .collect(Collectors.toList());
        List<Outputdata> outputList = inputList.stream().map(this::transform).collect(Collectors.toList());
        outputDataRepository.saveAll(outputList);
        return outputList;
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
        return Math.round((seconds / 60.0) * 10.0) / 10.0;
    }

    private String extractLastName(String user) {
        if (user == null || user.trim().isEmpty()) return "";
        String[] parts = user.trim().split(" ");
        return parts.length > 1 ? parts[parts.length - 1] : parts[0];
    }
}
