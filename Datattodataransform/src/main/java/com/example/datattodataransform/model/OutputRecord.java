package com.example.datattodataransform.model;

import com.azure.spring.data.cosmos.core.mapping.Container;
import com.azure.spring.data.cosmos.core.mapping.PartitionKey;
import lombok.Data;

@Data
@Container(containerName = "outputdata")
public class OutputRecord {
    private String recordId;
    private String id;
    private String service;
    private Sla sla;
    private String receivedTime;
    private String completedTime;
    private long durationMinutes;
    private boolean breached;
    private String owner;

    @Data
    public static class Sla {
        private String state;
    }

    @PartitionKey
    public String getRecordId() {
        return recordId;
    }
}
