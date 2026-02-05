package com.optum.datatojsontransformationoptum.service;

import com.optum.datatojsontransformationoptum.model.InputRecord;
import com.optum.datatojsontransformationoptum.model.OutputRecord;
import com.optum.datatojsontransformationoptum.repository.InputDataRepository;
import com.optum.datatojsontransformationoptum.repository.OutputDataRepository;
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
        List<InputRecord> inputRecords = StreamSupport
            .stream(inputDataRepository.findAll().spliterator(), false)
            .collect(Collectors.toList());
        List<OutputRecord> outputRecords = inputRecords.stream()
                .map(this::transform)
                .collect(Collectors.toList());
        outputDataRepository.saveAll(outputRecords);
        return outputRecords;
    }

    public OutputRecord transform(InputRecord input) {
        OutputRecord output = new OutputRecord();
        output.setId(input.getId());
        output.setRecordId(input.getId());
        output.setService("PRINT".equalsIgnoreCase(input.getSystem()) ? "sms" : input.getSystem());
        output.setSla(new OutputRecord.Sla(input.getStatus()));
        output.setReceivedTime(input.getReceivedTime());
        output.setCompletedTime(input.getCompletedTime());
        double duration = calculateDurationMinutes(input.getReceivedTime(), input.getCompletedTime());
        output.setDurationMinutes(duration);
        boolean breached = "FAILED".equalsIgnoreCase(input.getStatus()) || duration > 10.0;
        output.setBreached(breached);
        output.setOwner(extractOwner(input.getUser()));
        return output;
    }

    private double calculateDurationMinutes(String receivedTime, String completedTime) {
        Instant received = Instant.parse(receivedTime);
        Instant completed = Instant.parse(completedTime);
        Duration duration = Duration.between(received, completed);
        double minutes = duration.getSeconds() / 60.0;
        return Math.round(minutes * 10.0) / 10.0;
    }

    private String extractOwner(String user) {
        if (user == null || user.trim().isEmpty()) return "test";
        String[] parts = user.trim().split(" ");
        String lastName = parts.length > 1 ? parts[parts.length - 1] : parts[0];
        return "test " + lastName;
    }
}
