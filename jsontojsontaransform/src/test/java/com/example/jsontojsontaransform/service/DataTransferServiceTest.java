package com.example.jsontojsontaransform.service;

import com.example.jsontojsontaransform.model.InputRecord;
import com.example.jsontojsontaransform.model.OutputRecord;
import com.example.jsontojsontaransform.repository.InputRecordRepository;
import com.example.jsontojsontaransform.repository.OutputRecordRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import java.time.OffsetDateTime;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

class DataTransferServiceTest {
    private InputRecordRepository inputRepo;
    private OutputRecordRepository outputRepo;
    private DataTransferService service;

    @BeforeEach
    void setup() {
        inputRepo = mock(InputRecordRepository.class);
        outputRepo = mock(OutputRecordRepository.class);
        service = new DataTransferService(inputRepo, outputRepo);
    }

    @Test
    void testTransferData_TransformsCorrectly() {
        InputRecord in = new InputRecord();
        in.setId("abc");
        in.setSystem("myService");
        in.setStatus("COMPLETED");
        in.setReceivedTime(OffsetDateTime.now().minusMinutes(5).toString());
        in.setCompletedTime(OffsetDateTime.now().toString());
        in.setUser("owner1");
        when(inputRepo.findAll()).thenReturn(Arrays.asList(in));

        int count = service.transferData();
        assertThat(count).isEqualTo(1);
        ArgumentCaptor<List<OutputRecord>> captor = ArgumentCaptor.forClass(List.class);
        verify(outputRepo).saveAll(captor.capture());
        OutputRecord out = captor.getValue().get(0);
        assertThat(out.getId()).isEqualTo("abc");
        assertThat(out.getRecordId()).isEqualTo("abc");
        assertThat(out.getService()).isEqualTo("myService");
        assertThat(out.getSla().getState()).isEqualTo("COMPLETED");
        assertThat(out.getOwner()).isEqualTo("owner1");
        assertThat(out.getDurationMinutes()).isBetween(4L, 6L);
        assertThat(out.isBreached()).isFalse();
    }

    @Test
    void testTransferData_FailedOrTimeout() {
        InputRecord in = new InputRecord();
        in.setId("xyz");
        in.setSystem("svc");
        in.setStatus("FAILED");
        in.setReceivedTime(OffsetDateTime.now().minusMinutes(20).toString());
        in.setCompletedTime(OffsetDateTime.now().toString());
        in.setUser("user2");
        when(inputRepo.findAll()).thenReturn(Arrays.asList(in));
        service.transferData();
        ArgumentCaptor<List<OutputRecord>> captor = ArgumentCaptor.forClass(List.class);
        verify(outputRepo).saveAll(captor.capture());
        OutputRecord out = captor.getValue().get(0);
        assertThat(out.isBreached()).isTrue();
        assertThat(out.getDurationMinutes()).isGreaterThan(10L);
    }
}
