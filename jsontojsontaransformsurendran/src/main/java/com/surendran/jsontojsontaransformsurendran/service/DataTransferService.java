package com.surendran.jsontojsontaransformsurendran.service;

import com.surendran.jsontojsontaransformsurendran.model.InputRecord;
import com.surendran.jsontojsontaransformsurendran.model.OutputRecord;
import com.surendran.jsontojsontaransformsurendran.repository.InputRecordRepository;
import com.surendran.jsontojsontaransformsurendran.repository.OutputRecordRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    @Transactional
    public int transferData() {
        List<InputRecord> inputRecords = inputRecordRepository.findAll();
        List<OutputRecord> outputRecords = inputRecords.stream().map(this::transform).collect(Collectors.toList());
        outputRecordRepository.saveAll(outputRecords);
        return outputRecords.size();
    }

    private OutputRecord transform(InputRecord input) {
        String id = input.getId();
        String service = input.getSystem();
        String status = input.getStatus();
        String receivedTime = input.getReceivedTime();
        String completedTime = input.getCompletedTime();
        String owner = input.getUser();

        long durationMinutes = calculateDurationMinutes(receivedTime, completedTime);
        boolean breached = "FAILED".equalsIgnoreCase(status) || durationMinutes > 10;

        OutputRecord.Sla sla = new OutputRecord.Sla(status);
        return new OutputRecord(
                id, // recordId
                id, // id
                service,
                sla,
                receivedTime,
                completedTime,
                durationMinutes,
                breached,
                owner
        );
    }

    private long calculateDurationMinutes(String receivedTime, String completedTime) {
        try {
            OffsetDateTime rt = OffsetDateTime.parse(receivedTime, DateTimeFormatter.ISO_OFFSET_DATE_TIME);
            OffsetDateTime ct = OffsetDateTime.parse(completedTime, DateTimeFormatter.ISO_OFFSET_DATE_TIME);
            return Duration.between(rt, ct).toMinutes();
        } catch (Exception e) {
            return 0L;
        }
    }
}
