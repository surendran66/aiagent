package com.example.jsontojsontaransform.service;

import com.example.jsontojsontaransform.model.InputRecord;
import com.example.jsontojsontaransform.model.OutputRecord;
import com.example.jsontojsontaransform.repository.InputRecordRepository;
import com.example.jsontojsontaransform.repository.OutputRecordRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Duration;
import java.time.OffsetDateTime;
import java.time.format.DateTimeParseException;

@Service
@RequiredArgsConstructor
public class DataTransferService {
    private final InputRecordRepository inputRepo;
    private final OutputRecordRepository outputRepo;

    @Transactional
    public long transferAndTransform() {
        Iterable<InputRecord> inputs = inputRepo.findAll();
        long count = 0;
        for (InputRecord in : inputs) {
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
            outputRepo.save(out);
            count++;
        }
        return count;
    }

    private long calculateDurationMinutes(String start, String end) {
        if (start == null || end == null) return 0L;
        try {
            OffsetDateTime startTime = OffsetDateTime.parse(start);
            OffsetDateTime endTime = OffsetDateTime.parse(end);
            return Duration.between(startTime, endTime).toMinutes();
        } catch (DateTimeParseException e) {
            return 0L;
        }
    }
}
