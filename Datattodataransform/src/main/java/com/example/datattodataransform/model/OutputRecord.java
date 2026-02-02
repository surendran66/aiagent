package com.example.datattodataransform.model;

import com.azure.spring.data.cosmos.core.mapping.Container;
import com.azure.spring.data.cosmos.core.mapping.PartitionKey;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Container(containerName = "outputdata")
public class OutputRecord {
    private String recordId;
    private String id;
    private String service;
    private String owner;
    private String receivedTime;
    private String completedTime;
    private int durationMinutes;
    private boolean breached;
    private SLA sla;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class SLA {
        private String state;
    }

    @PartitionKey
    public String getRecordId() {
        return recordId;
    }
}
