package com.example.jsontojsontaransform.service;

import com.example.jsontojsontaransform.model.InputRecord;
import com.example.jsontojsontaransform.model.OutputRecord;
import com.example.jsontojsontaransform.repository.InputRecordRepository;
import com.example.jsontojsontaransform.repository.OutputRecordRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DataTransferService {
    private final InputRecordRepository inputRepo;
    private final OutputRecordRepository outputRepo;

    public int transferData() {
        Iterable<InputRecord> inputs = inputRepo.findAll();
        List<OutputRecord> outputs = transform(recordsToList(inputs));
        outputRepo.saveAll(outputs);
        return outputs.size();
    }

    private List<InputRecord> recordsToList(Iterable<InputRecord> it) {
        return ((List<InputRecord>) ((it instanceof List) ? it :
                (java.util.List<InputRecord>) org.springframework.util.StreamUtils.copyToList(it.iterator())));
    }

    private List<OutputRecord> transform(List<InputRecord> inputRecords) {
        return inputRecords.stream().map(this::mapRecord).collect(Collectors.toList());
    }

    private OutputRecord mapRecord(InputRecord in) {
        OutputRecord out = new OutputRecord();
        out.setRecordId(in.getId());
        out.setId(in.getId());
        out.setService(in.getSystem());
        OutputRecord.Sla sla = new OutputRecord.Sla();
        sla.setState(in.getStatus());
        out.setSla(sla);
        out.setReceivedTime(in.getReceivedTime());
        out.setCompletedTime(in.getCompletedTime());
        long durationMins = getDurationMinutes(in.getReceivedTime(), in.getCompletedTime());
        out.setDurationMinutes(durationMins);
        boolean breached = "FAILED".equalsIgnoreCase(in.getStatus()) || durationMins > 10;
        out.setBreached(breached);
        out.setOwner(in.getUser());
        return out;
    }

    private long getDurationMinutes(String receivedTime, String completedTime) {
        try {
            OffsetDateTime received = OffsetDateTime.parse(receivedTime);
            OffsetDateTime completed = OffsetDateTime.parse(completedTime);
            return Duration.between(received, completed).toMinutes();
        } catch (Exception e) {
            return 0L;
        }
    }
}
