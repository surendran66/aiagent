package com.secondautomation.service;

import com.secondautomation.model.InputRecord;
import com.secondautomation.model.OutputRecord;
import com.secondautomation.model.OutputRecord.Sla;
import com.secondautomation.repository.InputRecordRepository;
import com.secondautomation.repository.OutputRecordRepository;
import org.springframework.stereotype.Service;
import java.time.OffsetDateTime;
import java.time.format.DateTimeParseException;
import java.time.Duration;

@Service
public class DataTransferService {
    private final InputRecordRepository inputRepo;
    private final OutputRecordRepository outputRepo;

    public DataTransferService(InputRecordRepository inputRepo, OutputRecordRepository outputRepo) {
        this.inputRepo = inputRepo;
        this.outputRepo = outputRepo;
    }

    public void transferAndTransformData() {
        for (InputRecord in : inputRepo.findAll()) {
            long durationMinutes = calculateDurationMinutes(in.getReceivedTime(), in.getCompletedTime());
            boolean breached = durationMinutes > 0 && durationMinutes > 10;
            OutputRecord out = new OutputRecord(
                in.getId(),
                in.getId(),
                in.getService(),
                new Sla(in.getState()),
                in.getOwner(),
                in.getReceivedTime(),
                in.getCompletedTime(),
                durationMinutes,
                breached
            );
            outputRepo.save(out);
        }
    }

    long calculateDurationMinutes(String from, String to) {
        try {
            OffsetDateTime start = OffsetDateTime.parse(from);
            OffsetDateTime end = OffsetDateTime.parse(to);
            return Duration.between(start, end).toMinutes();
        } catch (DateTimeParseException | NullPointerException e) {
            return 0L;
        }
    }
}