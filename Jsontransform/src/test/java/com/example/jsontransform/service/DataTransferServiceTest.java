package com.example.jsontransform.service;

import com.example.jsontransform.model.InputRecord;
import com.example.jsontransform.model.OutputRecord;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;

class DataTransferServiceTest {
    DataTransferService service = new DataTransferService(null, null);

    @Test
    void testTransform() {
        String now = OffsetDateTime.now().withNano(0).format(DateTimeFormatter.ISO_OFFSET_DATE_TIME);
        String tenMinsLater = OffsetDateTime.now().plusMinutes(10).withNano(0).format(DateTimeFormatter.ISO_OFFSET_DATE_TIME);
        InputRecord input = new InputRecord("123", "CRM", "SUCCESS", "alice", now, tenMinsLater);
        OutputRecord out = service.transform(input);
        assertEquals("123", out.getRecordId());
        assertEquals("123", out.getId());
        assertEquals("CRM", out.getService());
        assertEquals("SUCCESS", out.getSla().getState());
        assertEquals("alice", out.getOwner());
        assertEquals(0, out.getDurationMinutes() - 10); // allow minor difference
        assertFalse(out.isBreached());
    }

    @Test
    void testBreachedStatus() {
        String now = OffsetDateTime.now().withNano(0).format(DateTimeFormatter.ISO_OFFSET_DATE_TIME);
        String later = OffsetDateTime.now().plusMinutes(5).withNano(0).format(DateTimeFormatter.ISO_OFFSET_DATE_TIME);
        InputRecord input = new InputRecord("1", "ERP", "FAILED", "bob", now, later);
        OutputRecord out = service.transform(input);
        assertTrue(out.isBreached());
    }

    @Test
    void testBreachedDuration() {
        String now = OffsetDateTime.now().withNano(0).format(DateTimeFormatter.ISO_OFFSET_DATE_TIME);
        String later = OffsetDateTime.now().plusMinutes(11).withNano(0).format(DateTimeFormatter.ISO_OFFSET_DATE_TIME);
        InputRecord input = new InputRecord("2", "BILLING", "SUCCESS", "eve", now, later);
        OutputRecord out = service.transform(input);
        assertTrue(out.isBreached());
    }
}
