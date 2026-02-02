package com.example.cosmostransfer.model;

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
    @PartitionKey
    private String recordId;
    private String id;
    private String service;
    private Sla sla;
    private String receivedTime;
    private String completedTime;
    private long durationMinutes;
    private boolean breached;
    private String owner;
}
