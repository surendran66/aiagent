package com.firstautomation.service;

import com.firstautomation.model.InputRecord;
import com.firstautomation.model.OutputRecord;
import java.time.Duration;
import java.time.LocalDateTime;

public class DataTransferService {
    public DataTransferService(Object x, Object y) {}

    public OutputRecord transform(InputRecord in) {
        LocalDateTime start = LocalDateTime.parse(in.getStart());
        LocalDateTime end = LocalDateTime.parse(in.getEnd());
        int duration = (int) Duration.between(start, end).toMinutes();
        boolean breached = duration > 10;
        if (!"FAILED".equals(in.getState())) {
            breached = false;
        }
        return new OutputRecord(
            in.getId(),
            in.getSystem(),
            new OutputRecord.Sla(in.getState()),
            duration,
            breached,
            in.getOwner()
        );
    }
}
