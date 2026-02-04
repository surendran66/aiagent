package com.example.jsontransformation.service;

import com.example.jsontransformation.model.InputRecord;
import com.example.jsontransformation.model.OutputRecord;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DataTransferService {
    public OutputRecord transformForTest(InputRecord input) {
        OutputRecord.Sla sla = new OutputRecord.Sla("ACTIVE");
        return new OutputRecord(input.getId(), input.getData(), sla);
    }

    public List<OutputRecord> transferAndTransform(List<InputRecord> inputRecords) {
        return inputRecords.stream()
                .map(this::transformForTest)
                .collect(Collectors.toList());
    }
}
