package com.example.Datattodataransform.service;

import com.example.Datattodataransform.model.InputRecord;
import com.example.Datattodataransform.model.OutputRecord;
import com.example.Datattodataransform.repository.InputRecordRepository;
import com.example.Datattodataransform.repository.OutputRecordRepository;
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
    private final InputRecordRepository inputRepository;
    private final OutputRecordRepository outputRepository;
    private static final DateTimeFormatter dtf = DateTimeFormatter.ISO_DATE_TIME;

    public int transfer() {
        List<InputRecord> inputRecords = inputRepository.findAll();
        List<OutputRecord> outputRecords = inputRecords.stream()
                .map(DataTransferService::transformRecord)
                .collect(Collectors.toList());
        outputRepository.saveAll(outputRecords);
        return outputRecords.size();
    }

    private static OutputRecord transformRecord(InputRecord in) {
        OutputRecord out = new OutputRecord();
        out.setRecordId(in.getId());
        out.setId(in.getId());
        out.setService(in.getSystem());
        out.setReceivedTime(in.getReceivedTime());
        out.setCompletedTime(in.getCompletedTime());
        out.setOwner(in.getUser());
        OutputRecord.Sla sla = new OutputRecord.Sla();
        sla.setState(in.getStatus());
        out.setSla(sla);

        long durationMinutes = 0L;
        try {
            LocalDateTime received = LocalDateTime.parse(in.getReceivedTime(), dtf);
            LocalDateTime completed = LocalDateTime.parse(in.getCompletedTime(), dtf);
            durationMinutes = Duration.between(received, completed).toMinutes();
        } catch (Exception ex) {
            durationMinutes = 0L;
        }
        out.setDurationMinutes(durationMinutes);
        boolean breached = "FAILED".equalsIgnoreCase(in.getStatus()) || durationMinutes > 10L;
        out.setBreached(breached);
        return out;
    }
}
