package com.example.cosmostransfer.model;

import com.azure.spring.data.cosmos.core.mapping.Container;
import com.azure.spring.data.cosmos.core.mapping.PartitionKey;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import java.time.OffsetDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Container(containerName = "outputdata")
public class OutputRecord {
    private String id;
    private String recordId;
    private String service;
    private Sla sla;
    private OffsetDateTime receivedTime;
    private OffsetDateTime completedTime;
    private long durationMinutes;
    private boolean breached;
    private String owner;

    @PartitionKey
    public String getRecordId() {
        return recordId;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Sla {
        private String state;
    }
}
