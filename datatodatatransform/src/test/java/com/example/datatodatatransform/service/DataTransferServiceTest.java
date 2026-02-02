package com.example.datatodatatransform.service;

import com.example.datatodatatransform.model.InputRecord;
import com.example.datatodatatransform.model.OutputRecord;
import com.example.datatodatatransform.repository.InputRecordRepository;
import com.example.datatodatatransform.repository.OutputRecordRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class DataTransferServiceTest {
    @Mock
    private InputRecordRepository inputRepo;
    @Mock
    private OutputRecordRepository outputRepo;
    @InjectMocks
    private DataTransferService service;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
        service = new DataTransferService(inputRepo, outputRepo);
    }

    @Test
    void testTransform_Success() {
        InputRecord in = new InputRecord("aaa1","ServiceX","READY","TestUser","2024-06-12T09:00:00Z","2024-06-12T09:11:00Z");
        OutputRecord out = service.transform(in);
        assertEquals(in.getId(), out.getId());
        assertEquals(in.getId(), out.getRecordId());
        assertEquals(in.getSystem(), out.getService());
        assertNotNull(out.getSla());
        assertEquals(in.getStatus(), out.getSla().getState());
        assertEquals(in.getUser(), out.getOwner());
        assertEquals(11L, out.getDurationMinutes());
        assertFalse(out.isBreached());
    }

    @Test
    void testTransform_Breached() {
        InputRecord in = new InputRecord("bbb2","Api","FAILED","TestUser2","2024-06-12T09:00:00Z","2024-06-12T09:20:01Z");
        OutputRecord out = service.transform(in);
        assertTrue(out.isBreached());
    }

    @Test
    void testTransferData_CallsRepo() {
        InputRecord rec = new InputRecord("z1", "Dev", "DONE", "xx", "2024-01-01T12:00:00Z", "2024-01-01T12:02:00Z");
        when(inputRepo.findAll()).thenReturn(Arrays.asList(rec));
        service.transferData();
        verify(inputRepo, times(1)).findAll();
        verify(outputRepo, times(1)).save(any(OutputRecord.class));
    }
}
