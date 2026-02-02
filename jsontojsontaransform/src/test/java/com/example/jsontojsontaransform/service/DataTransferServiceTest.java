package com.example.jsontojsontaransform.service;

import com.example.jsontojsontaransform.model.InputRecord;
import com.example.jsontojsontaransform.model.OutputRecord;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

class DataTransferServiceTest {

    @Test
    void testTransformLogic() {
        DataTransferService svc = new DataTransferService(null, null);
        InputRecord in = new InputRecord();
        in.setId("abc123");
        in.setSystem("svc");
        in.setStatus("FAILED");
        in.setUser("theuser");
        LocalDateTime now = LocalDateTime.now();
        in.setReceivedTime(now.minusMinutes(11).format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));
        in.setCompletedTime(now.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));
        OutputRecord out = svc.transform(in);
        assertEquals(in.getId(), out.getRecordId());
        assertEquals("svc", out.getService());
        assertEquals("FAILED", out.getSla().getState());
        assertEquals(11L, out.getDurationMinutes());
        assertTrue(out.isBreached());
        assertEquals("theuser", out.getOwner());
    }

    @Test
    void testTransformWithShortDuration() {
        DataTransferService svc = new DataTransferService(null, null);
        InputRecord in = new InputRecord();
        in.setId("abc456");
        in.setSystem("testsvc");
        in.setStatus("SUCCESS");
        in.setUser("user2");
        LocalDateTime now = LocalDateTime.now();
        in.setReceivedTime(now.minusMinutes(5).format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));
        in.setCompletedTime(now.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));
        OutputRecord out = svc.transform(in);
        assertFalse(out.isBreached());
        assertEquals(5L, out.getDurationMinutes());
    }

    @Test
    void testTransformInvalidDates() {
        DataTransferService svc = new DataTransferService(null, null);
        InputRecord in = new InputRecord();
        in.setId("abc789");
        in.setSystem("testsvc");
        in.setStatus("FAILED");
        in.setUser("user3");
        in.setReceivedTime("notadate");
        in.setCompletedTime("alsonotadate");
        OutputRecord out = svc.transform(in);
        assertEquals(0L, out.getDurationMinutes());
        assertTrue(out.isBreached());
    }
}
