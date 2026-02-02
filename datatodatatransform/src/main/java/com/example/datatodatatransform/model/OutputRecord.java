package com.example.datatodatatransform.model;

import com.azure.spring.data.cosmos.core.mapping.Container;
import com.azure.spring.data.cosmos.core.mapping.PartitionKey;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Container(containerName = "outputdata")
public class OutputRecord {
    private String id;
    private String recordId;
    private String service;
    private Sla sla;
    private String owner;
    private String receivedTime;
    private String completedTime;
    private long durationMinutes;
    private boolean breached;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Sla {
        private String state;
    }

    @PartitionKey
    public String getRecordId() {
        return recordId;
    }
}
