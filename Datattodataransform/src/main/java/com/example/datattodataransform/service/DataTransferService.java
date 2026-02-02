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
import java.util.stream.StreamSupport;

@Service
@RequiredArgsConstructor
public class DataTransferService {
    private final InputRecordRepository inputRepository;
    private final OutputRecordRepository outputRepository;

    public long transfer() {
        Iterable<InputRecord> inputs = inputRepository.findAll();
        long count = StreamSupport.stream(inputs.spliterator(), false)
            .map(this::transform)
            .peek(outputRepository::save)
            .count();
        return count;
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
        OffsetDateTime rt = OffsetDateTime.parse(in.getReceivedTime(), DateTimeFormatter.ISO_OFFSET_DATE_TIME);
        OffsetDateTime ct = OffsetDateTime.parse(in.getCompletedTime(), DateTimeFormatter.ISO_OFFSET_DATE_TIME);
        long diff = Duration.between(rt, ct).toMinutes();
        out.setDurationMinutes(diff);
        boolean breached = "FAILED".equalsIgnoreCase(in.getStatus()) || diff > 10;
        out.setBreached(breached);
        out.setOwner(in.getUser());
        return out;
    }
}
