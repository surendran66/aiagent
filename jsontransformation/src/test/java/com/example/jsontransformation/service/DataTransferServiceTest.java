package com.example.jsontransformation.service;

import com.example.jsontransformation.model.InputRecord;
import com.example.jsontransformation.model.OutputRecord;
import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class DataTransferServiceTest {
    private final DataTransferService service = new DataTransferService();

    @Test
    void testTransform() {
        InputRecord input = new InputRecord("1", "testdata");
        OutputRecord output = service.transformForTest(input);
        assertEquals(input.getId(), output.getId());
        assertEquals(input.getData(), output.getData());
        assertNotNull(output.getSla());
        assertEquals("ACTIVE", output.getSla().getState());
    }

    @Test
    void testSlaState() {
        InputRecord input = new InputRecord("2", "otherdata");
        List<OutputRecord> outputs = service.transferAndTransform(List.of(input));
        assertEquals(1, outputs.size());
        assertEquals("ACTIVE", outputs.get(0).getSla().getState());
    }
}
