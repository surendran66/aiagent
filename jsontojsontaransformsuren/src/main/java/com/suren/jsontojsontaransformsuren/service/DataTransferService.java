package com.suren.jsontojsontaransformsuren.service;

import com.suren.jsontojsontaransformsuren.model.InputRecord;
import com.suren.jsontojsontaransformsuren.model.OutputRecord;
import com.suren.jsontojsontaransformsuren.repository.InputRecordRepository;
import com.suren.jsontojsontaransformsuren.repository.OutputRecordRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.OffsetDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DataTransferService {
    private final InputRecordRepository inputRecordRepository;
    private final OutputRecordRepository outputRecordRepository;

    public int transfer() {
        List<InputRecord> inputRecords = (List<InputRecord>) inputRecordRepository.findAll();
        int count = 0;
        for (InputRecord in : inputRecords) {
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
            boolean breached = ("FAILED".equalsIgnoreCase(in.getStatus()) || duration > 10);
            out.setBreached(breached);
            out.setOwner(in.getUser());
            outputRecordRepository.save(out);
            count++;
        }
        return count;
    }
    
    private long calculateDurationMinutes(String receivedTime, String completedTime) {
        try {
            OffsetDateTime start = OffsetDateTime.parse(receivedTime);
            OffsetDateTime end = OffsetDateTime.parse(completedTime);
            return Duration.between(start, end).toMinutes();
        } catch (Exception ex) {
            return 0L;
        }
    }
}
