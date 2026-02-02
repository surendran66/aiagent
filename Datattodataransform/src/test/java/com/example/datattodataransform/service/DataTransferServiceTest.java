package com.example.datattodataransform.service;

import com.example.datattodataransform.model.InputRecord;
import com.example.datattodataransform.model.OutputRecord;
import com.example.datattodataransform.repository.InputRecordRepository;
import com.example.datattodataransform.repository.OutputRecordRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;
import java.time.OffsetDateTime;
import java.util.Collections;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

class DataTransferServiceTest {
    private InputRecordRepository inputRepo;
    private OutputRecordRepository outputRepo;
    private DataTransferService service;

    @BeforeEach
    void setUp() {
        inputRepo = mock(InputRecordRepository.class);
        outputRepo = mock(OutputRecordRepository.class);
        service = new DataTransferService(inputRepo, outputRepo);
    }

    @Test
    void transferAll_transformsAndSavesRecords() {
        InputRecord input = new InputRecord();
        input.setId("1");
        input.setSystem("crm");
        input.setStatus("FAILED");
        input.setUser("alice");
        String now = OffsetDateTime.now().toString();
        input.setReceivedTime(now);
        input.setCompletedTime(now);
        when(inputRepo.findAll()).thenReturn(Collections.singletonList(input));
        ArgumentCaptor<List<OutputRecord>> captor = ArgumentCaptor.forClass(List.class);
        when(outputRepo.saveAll(captor.capture())).thenReturn(null);

        long count = service.transferAll();

        assertThat(count).isEqualTo(1);
        OutputRecord out = captor.getValue().get(0);
        assertThat(out.getId()).isEqualTo("1");
        assertThat(out.getRecordId()).isEqualTo("1");
        assertThat(out.getService()).isEqualTo("crm");
        assertThat(out.getOwner()).isEqualTo("alice");
        assertThat(out.getDurationMinutes()).isEqualTo(0);
        assertThat(out.isBreached()).isTrue();
        assertThat(out.getSla().getState()).isEqualTo("FAILED");
    }
}
