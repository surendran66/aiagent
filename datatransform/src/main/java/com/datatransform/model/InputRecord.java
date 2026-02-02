package com.datatransform.model;

import com.azure.spring.data.cosmos.core.mapping.Container;
import com.azure.spring.data.cosmos.core.mapping.PartitionKey;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Container(containerName = "inputdata")
public class InputRecord {
    @PartitionKey
    private String id;
    private String system;
    private String status;
    private String receivedTime; // ISO-8601 String
    private String completedTime; // ISO-8601 String
    private String user;
}
