package com.surendran.jsontojsontaransformsurendran.service;

import com.surendran.jsontojsontaransformsurendran.model.InputRecord;
import com.surendran.jsontojsontaransformsurendran.model.OutputRecord;
import com.surendran.jsontojsontaransformsurendran.repository.InputRecordRepository;
import com.surendran.jsontojsontaransformsurendran.repository.OutputRecordRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.time.OffsetDateTime;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.*;

class DataTransferServiceTest {
    @Mock
    InputRecordRepository inputRecordRepository;
    @Mock
    OutputRecordRepository outputRecordRepository;
    @InjectMocks
    DataTransferService dataTransferService;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testTransferData_andTransformation() {
        String now = OffsetDateTime.now().toString();
        String later = OffsetDateTime.now().plusMinutes(12).toString();
        InputRecord rec = new InputRecord("123", "system1", "COMPLETED", now, later, "user1");
        when(inputRecordRepository.findAll()).thenReturn(Collections.singletonList(rec));
        when(outputRecordRepository.saveAll(anyList())).thenReturn(null);

        int result = dataTransferService.transferData();
        assertEquals(1, result);
        ArgumentCaptor<List<OutputRecord>> captor = ArgumentCaptor.forClass(List.class);
        verify(outputRecordRepository, times(1)).saveAll(captor.capture());
        OutputRecord out = captor.getValue().get(0);
        assertEquals("123", out.getId());
        assertEquals("system1", out.getService());
        assertEquals("COMPLETED", out.getSla().getState());
        assertEquals("user1", out.getOwner());
        assertTrue(out.getDurationMinutes() >= 12);
        assertTrue(out.isBreached());
    }
}
