package com.example.datattodataransform.service;

import com.azure.spring.data.cosmos.repository.CosmosRepository;
import com.example.datattodataransform.model.InputRecord;
import com.example.datattodataransform.model.OutputRecord;
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
    private final InputRecordRepository inputRepo;
    private final OutputRecordRepository outputRepo;

    public int transfer() {
        List<InputRecord> inputs = inputRepo.findAll();
        List<OutputRecord> outputs = inputs.stream().map(this::transform).collect(Collectors.toList());
        outputRepo.saveAll(outputs);
        return outputs.size();
    }

    private OutputRecord transform(InputRecord input) {
        String id = input.getId();
        String received = input.getReceivedTime();
        String completed = input.getCompletedTime();
        long duration = 0L;
        boolean breached = false;
        try {
            OffsetDateTime rec = OffsetDateTime.parse(received, DateTimeFormatter.ISO_OFFSET_DATE_TIME);
            OffsetDateTime comp = OffsetDateTime.parse(completed, DateTimeFormatter.ISO_OFFSET_DATE_TIME);
            duration = Duration.between(rec, comp).toMinutes();
        } catch (Exception ex) {
            duration = 0L;
        }
        breached = "FAILED".equalsIgnoreCase(input.getStatus()) || duration > 10;
        return OutputRecord.builder()
                .recordId(id)
                .id(id)
                .service(input.getSystem())
                .sla(OutputRecord.Sla.builder().state(input.getStatus()).build())
                .receivedTime(received)
                .completedTime(completed)
                .durationMinutes(duration)
                .breached(breached)
                .owner(input.getUser())
                .build();
    }

    public interface InputRecordRepository extends CosmosRepository<InputRecord, String> {}
    public interface OutputRecordRepository extends CosmosRepository<OutputRecord, String> {}
}
