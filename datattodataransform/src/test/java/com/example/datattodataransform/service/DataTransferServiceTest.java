package com.example.datattodataransform.service;

import com.example.datattodataransform.model.InputRecord;
import com.example.datattodataransform.model.OutputRecord;
import org.junit.jupiter.api.Test;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import static org.junit.jupiter.api.Assertions.*;

public class DataTransferServiceTest {

    @Test
    void testTransform() {
        DataTransferService service = new DataTransferService(null, null);
        InputRecord in = new InputRecord(
                "1",
                "svcA",
                "FAILED",
                "alice",
                "2024-01-01T10:00:00+00:00",
                "2024-01-01T10:20:00+00:00"
        );
        OutputRecord out = service.transform(in);
        assertEquals(in.getId(), out.getRecordId());
        assertEquals(in.getSystem(), out.getService());
        assertEquals(in.getStatus(), out.getSla().getState());
        assertEquals(20, out.getDurationMinutes());
        assertTrue(out.isBreached());
        assertEquals(in.getUser(), out.getOwner());
    }
}
