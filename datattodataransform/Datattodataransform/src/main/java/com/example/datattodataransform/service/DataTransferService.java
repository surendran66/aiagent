package com.example.datattodataransform.service;

import com.example.datattodataransform.model.InputRecord;
import com.example.datattodataransform.model.OutputRecord;
import com.example.datattodataransform.repository.InputRecordRepository;
import com.example.datattodataransform.repository.OutputRecordRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.Duration;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;

@Service
@RequiredArgsConstructor
public class DataTransferService {
    private final InputRecordRepository inputRepository;
    private final OutputRecordRepository outputRepository;
    private static final DateTimeFormatter FMT = DateTimeFormatter.ISO_OFFSET_DATE_TIME;

    @Transactional
    public int transfer() {
        int counter = 0;
        for (InputRecord input : inputRepository.findAll()) {
            OutputRecord output = mapToOutput(input);
            outputRepository.save(output);
            counter++;
        }
        return counter;
    }

    private OutputRecord mapToOutput(InputRecord in) {
        OffsetDateTime received = OffsetDateTime.parse(in.getReceivedTime(), FMT);
        OffsetDateTime completed = OffsetDateTime.parse(in.getCompletedTime(), FMT);
        long mins = Duration.between(received, completed).toMinutes();
        boolean breached = 
            "FAILED".equalsIgnoreCase(in.getStatus()) || mins > 10;
        OutputRecord.Sla sla = new OutputRecord.Sla(in.getStatus());
        return new OutputRecord(
            in.getId(),
            in.getId(),
            in.getSystem(),
            sla,
            in.getReceivedTime(),
            in.getCompletedTime(),
            mins,
            breached,
            in.getUser()
        );
    }
}
