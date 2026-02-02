package com.example.cosmostransfer.model;

import com.azure.spring.data.cosmos.core.mapping.Container;
import com.azure.spring.data.cosmos.core.mapping.PartitionKey;
import lombok.Data;

import java.time.OffsetDateTime;

@Data
@Container(containerName = "inputdata")
public class InputRecord {
    private String id;
    private String system;
    private String status;
    private OffsetDateTime receivedTime;
    private OffsetDateTime completedTime;
    private String user;

    @PartitionKey
    public String getId() {
        return id;
    }
}
