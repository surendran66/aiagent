package com.example.datattodataransform.service;

import com.example.datattodataransform.model.InputRecord;
import com.example.datattodataransform.model.OutputRecord;
import com.example.datattodataransform.repository.InputRecordRepository;
import com.example.datattodataransform.repository.OutputRecordRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DataTransferService {
    private final InputRecordRepository inputRecordRepository;
    private final OutputRecordRepository outputRecordRepository;

    public long transferAll() {
        List<InputRecord> inputRecords = inputRecordRepository.findAll();
        List<OutputRecord> outputRecords = inputRecords.stream().map(this::transform).collect(Collectors.toList());
        outputRecordRepository.saveAll(outputRecords);
        return outputRecords.size();
    }

    private OutputRecord transform(InputRecord in) {
        OutputRecord out = new OutputRecord();
        out.setRecordId(in.getId());
        out.setId(in.getId());
        out.setService(in.getSystem());
        OutputRecord.Sla sla = new OutputRecord.Sla();
        sla.setState(in.getStatus());
        out.setSla(sla);
        out.setReceivedTime(in.getReceivedTime());
        out.setCompletedTime(in.getCompletedTime());
        long duration = calculateDurationMinutes(in.getReceivedTime(), in.getCompletedTime());
        out.setDurationMinutes(duration);
        out.setBreached("FAILED".equalsIgnoreCase(in.getStatus()) || duration > 10);
        out.setOwner(in.getUser());
        return out;
    }

    private long calculateDurationMinutes(String start, String end) {
        try {
            OffsetDateTime s = OffsetDateTime.parse(start, DateTimeFormatter.ISO_OFFSET_DATE_TIME);
            OffsetDateTime e = OffsetDateTime.parse(end, DateTimeFormatter.ISO_OFFSET_DATE_TIME);
            return Duration.between(s, e).toMinutes();
        } catch (Exception ex) {
            return 0L;
        }
    }
}
