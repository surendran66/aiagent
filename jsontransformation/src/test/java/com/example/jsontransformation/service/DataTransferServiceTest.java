package com.example.jsontransformation.service;

import com.example.jsontransformation.model.InputRecord;
import com.example.jsontransformation.model.OutputRecord;
import com.example.jsontransformation.model.OutputRecord.Sla;
import com.example.jsontransformation.repository.InputRecordRepository;
import com.example.jsontransformation.repository.OutputRecordRepository;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class DataTransferServiceTest {
    @Test
    void testTransform() {
        InputRecord input = new InputRecord();
        input.setId("1");
        input.setName("Test Name");
        input.setType("TypeA");
        input.setStatus("Active");
        input.setPriority("High");
        input.setSla("Open");

        // Use null for repositories since we only test transform logic
        DataTransferService service = new DataTransferService(null, null);
        OutputRecord output = service.transform(input);

        assertNotNull(output);
        assertEquals("1", output.getId());
        assertEquals("Test Name", output.getName());
        assertEquals("TypeA", output.getType());
        assertEquals("Active", output.getStatus());
        assertEquals("High", output.getPriority());
        assertNotNull(output.getSla());
        assertEquals("Open", output.getSla().getState());
    }
}
