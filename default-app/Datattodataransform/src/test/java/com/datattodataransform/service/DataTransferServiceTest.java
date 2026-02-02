package com.datattodataransform.service;

import com.datattodataransform.model.InputRecord;
import com.datattodataransform.model.OutputRecord;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class DataTransferServiceTest {
    private final DataTransferService service = new DataTransferService();

    @Test
    void testTransformationLogic() {
        InputRecord input = new InputRecord();
        input.setId("1001");
        input.setSystem("HR");
        input.setStatus("SUCCESS");
        input.setUser("jane");
        input.setReceivedTime("2024-06-07T08:00:00Z");
        input.setCompletedTime("2024-06-07T08:15:00Z");

        OutputRecord output = service.transform(input);
        assertEquals("1001", output.getId());
        assertEquals("1001", output.getRecordId());
        assertEquals("HR", output.getService());
        assertNotNull(output.getSla());
        assertEquals("SUCCESS", output.getSla().getState());
        assertEquals("2024-06-07T08:00:00Z", output.getReceivedTime());
        assertEquals("2024-06-07T08:15:00Z", output.getCompletedTime());
        assertEquals(15L, output.getDurationMinutes());
        assertFalse(output.isBreached());
        assertEquals("jane", output.getOwner());

        input.setStatus("FAILED");
        output = service.transform(input);
        assertTrue(output.isBreached());
    }
}
