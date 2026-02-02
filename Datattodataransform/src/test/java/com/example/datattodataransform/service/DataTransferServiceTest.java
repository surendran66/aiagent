package com.example.datattodataransform.service;

import com.example.datattodataransform.model.InputRecord;
import com.example.datattodataransform.repository.InputRecordRepository;
import com.example.datattodataransform.repository.OutputRecordRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import java.util.Collections;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;

public class DataTransferServiceTest {
    @Test
    void testTransferLogic() {
        InputRecordRepository in = Mockito.mock(InputRecordRepository.class);
        OutputRecordRepository out = Mockito.mock(OutputRecordRepository.class);
        InputRecord record = new InputRecord();
        record.setId("1");
        record.setSystem("test-system");
        record.setStatus("COMPLETED");
        record.setUser("owner1");
        record.setReceivedTime("2024-06-20T10:00:00+00:00");
        record.setCompletedTime("2024-06-20T10:05:00+00:00");
        Mockito.when(in.findAll()).thenReturn(Collections.singletonList(record));
        DataTransferService svc = new DataTransferService(in, out);
        long result = svc.transfer();
        verify(out).save(any());
        assert result == 1;
    }
}
