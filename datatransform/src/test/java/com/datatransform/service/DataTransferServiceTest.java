package com.datatransform.service;

import com.datatransform.model.InputRecord;
import com.datatransform.model.OutputRecord;
import com.datatransform.repository.InputRecordRepository;
import com.datatransform.repository.OutputRecordRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.OffsetDateTime;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class DataTransferServiceTest {
    @Mock
    private InputRecordRepository inputRepository;
    @Mock
    private OutputRecordRepository outputRepository;
    @InjectMocks
    private DataTransferService dataTransferService;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testTransferDataAndTransformation() {
        InputRecord in1 = new InputRecord();
        in1.setId("id1");
        in1.setSystem("svc1");
        in1.setStatus("FAILED");
        in1.setReceivedTime(OffsetDateTime.now().minusMinutes(15).toString());
        in1.setCompletedTime(OffsetDateTime.now().toString());
        in1.setUser("user1");

        InputRecord in2 = new InputRecord();
        in2.setId("id2");
        in2.setSystem("svc2");
        in2.setStatus("SUCCESS");
        in2.setReceivedTime(OffsetDateTime.now().minusMinutes(5).toString());
        in2.setCompletedTime(OffsetDateTime.now().toString());
        in2.setUser("user2");

        when(inputRepository.findAll()).thenReturn(Arrays.asList(in1, in2));
        when(outputRepository.saveAll(anyList())).thenReturn(null);

        int result = dataTransferService.transferData();
        assertEquals(2, result);
    }

    @Test
    void testTransformCreatesBreachedWhenFailed() {
        InputRecord record = new InputRecord();
        record.setId("5");
        record.setSystem("systemA");
        record.setStatus("FAILED");
        record.setReceivedTime(OffsetDateTime.now().minusMinutes(12).toString());
        record.setCompletedTime(OffsetDateTime.now().toString());
        record.setUser("owner1");

        DataTransferService service = new DataTransferService(null, null);
        OutputRecord out = service.transform(record);
        assertTrue(out.isBreached());
        assertEquals("FAILED", out.getSla().getState());
        assertEquals("owner1", out.getOwner());
    }
}
