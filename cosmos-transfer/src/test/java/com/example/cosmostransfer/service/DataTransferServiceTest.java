package com.example.cosmostransfer.service;

import com.example.cosmostransfer.model.DataItem;
import com.example.cosmostransfer.model.OutputDataItem;
import com.example.cosmostransfer.repository.InputDataRepository;
import com.example.cosmostransfer.repository.OutputDataRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class DataTransferServiceTest {
    private InputDataRepository inputRepo;
    private OutputDataRepository outputRepo;
    private DataTransferService service;

    @BeforeEach
    void setup() {
        inputRepo = mock(InputDataRepository.class);
        outputRepo = mock(OutputDataRepository.class);
        service = new DataTransferService(inputRepo, outputRepo);
    }

    @Test
    void transferAllData_shouldTransferAllItems() {
        DataItem item1 = new DataItem("1", "pk1", "data1");
        DataItem item2 = new DataItem("2", "pk2", "data2");
        List<DataItem> items = Arrays.asList(item1, item2);

        when(inputRepo.findAll()).thenReturn(items);
        when(outputRepo.saveAll(Mockito.anyList())).thenReturn(
                Arrays.asList(
                        new OutputDataItem("1", "pk1", "data1"),
                        new OutputDataItem("2", "pk2", "data2")
                ));

        int transferred = service.transferAllData();
        assertEquals(2, transferred);
        verify(inputRepo, times(1)).findAll();
        verify(outputRepo, times(1)).saveAll(Mockito.anyList());
    }
}
