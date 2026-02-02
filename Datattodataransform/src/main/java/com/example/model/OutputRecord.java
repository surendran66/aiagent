package com.example.model;

import com.azure.spring.data.cosmos.core.mapping.Container;
import com.azure.spring.data.cosmos.core.mapping.PartitionKey;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Container(containerName = "outputdata")
public class OutputRecord {
    @PartitionKey
    private String recordId;
    private String id;
    private String service;
    private SLA sla;
    private String receivedTime;
    private String completedTime;
    private long durationMinutes;
    private boolean breached;
    private String owner;
}
