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
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DataTransferService {
    private final InputRecordRepository inputRecordRepo;
    private final OutputRecordRepository outputRecordRepo;
    private static final DateTimeFormatter ISO_FORMAT = DateTimeFormatter.ISO_OFFSET_DATE_TIME;

    public int transferAndTransform() {
        Iterable<InputRecord> records = inputRecordRepo.findAll();
        List<OutputRecord> outList = new ArrayList<>();
        for (InputRecord in : records) {
            OutputRecord out = new OutputRecord();
            out.setRecordId(in.getId());
            out.setId(in.getId());
            out.setService(in.getSystem());
            out.setOwner(in.getUser());
            out.setReceivedTime(in.getReceivedTime());
            out.setCompletedTime(in.getCompletedTime());
            // Date parse
            int durationMin = calculateDurationMins(in.getReceivedTime(), in.getCompletedTime());
            out.setDurationMinutes(durationMin);
            boolean breached = "FAILED".equalsIgnoreCase(in.getStatus()) || durationMin > 10;
            out.setBreached(breached);
            out.setSla(new OutputRecord.SLA(in.getStatus()));
            outList.add(out);
        }
        outputRecordRepo.saveAll(outList);
        return outList.size();
    }

    private int calculateDurationMins(String from, String to) {
        try {
            OffsetDateTime oFrom = OffsetDateTime.parse(from, ISO_FORMAT);
            OffsetDateTime oTo = OffsetDateTime.parse(to, ISO_FORMAT);
            return (int) Duration.between(oFrom, oTo).abs().toMinutes();
        } catch (Exception ex) {
            return 0;
        }
    }
}
