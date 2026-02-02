package com.datatransform.service;

import com.datatransform.model.InputRecord;
import com.datatransform.model.OutputRecord;
import com.datatransform.repository.InputRecordRepository;
import com.datatransform.repository.OutputRecordRepository;
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
        List<InputRecord> inputs = inputRepo.findAll();
        List<OutputRecord> outputs = inputs.stream().map(this::transform).collect(Collectors.toList());
        outputRepo.saveAll(outputs);
        return outputs.size();
    }

    private OutputRecord transform(InputRecord input) {
        OutputRecord output = new OutputRecord();
        output.setRecordId(input.getId());
        output.setId(input.getId());
        output.setService(input.getSystem());
        OutputRecord.Sla sla = new OutputRecord.Sla();
        sla.setState(input.getStatus());
        output.setSla(sla);
        output.setReceivedTime(input.getReceivedTime());
        output.setCompletedTime(input.getCompletedTime());
        long minutes = calculateDurationMinutes(input.getReceivedTime(), input.getCompletedTime());
        output.setDurationMinutes(minutes);
        boolean breached = "FAILED".equalsIgnoreCase(input.getStatus()) || minutes > 10;
        output.setBreached(breached);
        output.setOwner(input.getUser());
        return output;
    }

    private long calculateDurationMinutes(String start, String end) {
        try {
            OffsetDateTime startTime = OffsetDateTime.parse(start);
            OffsetDateTime endTime = OffsetDateTime.parse(end);
            return Duration.between(startTime, endTime).toMinutes();
        } catch (Exception e) {
            return 0;
        }
    }
}
