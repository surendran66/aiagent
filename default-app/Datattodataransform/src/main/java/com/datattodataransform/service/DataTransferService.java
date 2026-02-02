package com.datattodataransform.service;

import com.datattodataransform.model.InputRecord;
import com.datattodataransform.model.OutputRecord;
import com.datattodataransform.model.OutputRecord.Sla;
import com.azure.spring.data.cosmos.repository.CosmosRepository;
import com.azure.spring.data.cosmos.repository.ReactiveCosmosRepository;
import com.azure.spring.data.cosmos.core.CosmosTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import java.time.Duration;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DataTransferService {
    @Autowired
    private CosmosTemplate cosmosTemplate;

    @Value("${azure.cosmos.database}")
    private String databaseName;

    public int transfer() {
        String inputContainer = "inputdata";
        String outputContainer = "outputdata";
        List<InputRecord> inputRecords = cosmosTemplate.findAll(InputRecord.class, inputContainer);
        List<OutputRecord> outputRecords = inputRecords.stream()
                .map(this::transform)
                .collect(Collectors.toList());
        outputRecords.forEach(record -> cosmosTemplate.insert(outputContainer, record));
        return outputRecords.size();
    }

    public OutputRecord transform(InputRecord input) {
        OutputRecord output = new OutputRecord();
        output.setId(input.getId());
        output.setRecordId(input.getId());
        output.setService(input.getSystem());
        Sla sla = new Sla();
        sla.setState(input.getStatus());
        output.setSla(sla);
        output.setReceivedTime(input.getReceivedTime());
        output.setCompletedTime(input.getCompletedTime());
        long duration = calculateDurationMinutes(input.getReceivedTime(), input.getCompletedTime());
        output.setDurationMinutes(duration);
        output.setBreached("FAILED".equalsIgnoreCase(input.getStatus()) || duration > 10);
        output.setOwner(input.getUser());
        return output;
    }

    private long calculateDurationMinutes(String receivedTime, String completedTime) {
        try {
            OffsetDateTime received = OffsetDateTime.parse(receivedTime, DateTimeFormatter.ISO_OFFSET_DATE_TIME);
            OffsetDateTime completed = OffsetDateTime.parse(completedTime, DateTimeFormatter.ISO_OFFSET_DATE_TIME);
            return Duration.between(received, completed).toMinutes();
        } catch (Exception e) {
            return 0;
        }
    }
}
