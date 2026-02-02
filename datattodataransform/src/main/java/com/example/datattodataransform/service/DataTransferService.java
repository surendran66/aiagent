package com.example.datattodataransform.service;

import com.azure.spring.data.cosmos.repository.CosmosRepository;
import com.example.datattodataransform.model.InputRecord;
import com.example.datattodataransform.model.OutputRecord;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageRequest;
import java.time.Duration;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DataTransferService {

    private final InputRecordRepository inputRepository;
    private final OutputRecordRepository outputRepository;

    public int transfer() {
        List<InputRecord> records = inputRepository.findAll();
        List<OutputRecord> outputRecords = records.stream().map(this::transform).collect(Collectors.toList());
        outputRepository.saveAll(outputRecords);
        return outputRecords.size();
    }

    public OutputRecord transform(InputRecord input) {
        // receivedTime & completedTime are assumed ISO_OFFSET_DATE_TIME
        OffsetDateTime receiveTime = OffsetDateTime.parse(input.getReceivedTime(), DateTimeFormatter.ISO_OFFSET_DATE_TIME);
        OffsetDateTime completeTime = OffsetDateTime.parse(input.getCompletedTime(), DateTimeFormatter.ISO_OFFSET_DATE_TIME);
        long duration = Duration.between(receiveTime, completeTime).toMinutes();
        boolean breached = "FAILED".equalsIgnoreCase(input.getStatus()) || duration > 10;
        OutputRecord.Sla sla = new OutputRecord.Sla(input.getStatus());
        return new OutputRecord(
                input.getId(), // recordId
                input.getId(),
                input.getSystem(),
                sla,
                input.getReceivedTime(),
                input.getCompletedTime(),
                duration,
                breached,
                input.getUser()
        );
    }
}
