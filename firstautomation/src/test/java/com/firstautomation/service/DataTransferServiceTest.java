package com.firstautomation.service;

import com.firstautomation.model.InputRecord;
import com.firstautomation.model.OutputRecord;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class DataTransferServiceTest {
    @Test
    void testTransformCorrectMappingAndBreach() {
        InputRecord in = new InputRecord(
            "abc123",
            "test-system",
            "FAILED",
            "2024-06-01T10:00:00",
            "2024-06-01T10:12:00",
            "usrA"
        );
        DataTransferService svc = new DataTransferService(null, null);
        OutputRecord out = svc.transform(in);
        assertEquals(in.getId(), out.getRecordId());
        assertEquals("test-system", out.getService());
        assertEquals("FAILED", out.getSla().getState());
        assertEquals(12, out.getDurationMinutes());
        assertTrue(out.isBreached());
        assertEquals("usrA", out.getOwner());
    }

    @Test
    void testTransformNoBreach() {
        InputRecord in = new InputRecord(
            "xyz789",
            "appX",
            "SUCCESS",
            "2024-06-01T11:00:00",
            "2024-06-01T11:05:00",
            "usrB"
        );
        DataTransferService svc = new DataTransferService(null, null);
        OutputRecord out = svc.transform(in);
        assertFalse(out.isBreached());
        assertEquals(5, out.getDurationMinutes());
    }
}
