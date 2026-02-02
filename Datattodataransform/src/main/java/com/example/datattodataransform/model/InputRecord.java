package com.example.datattodataransform.model;

import com.azure.spring.data.cosmos.core.mapping.Container;
import com.azure.spring.data.cosmos.core.mapping.PartitionKey;
import lombok.Data;

@Data
@Container(containerName = "inputdata")
public class InputRecord {
    @PartitionKey
    private String id;
    private String system;
    private String status;
    private String user;
    private String receivedTime; // ISO 8601 format.
    private String completedTime;
}
