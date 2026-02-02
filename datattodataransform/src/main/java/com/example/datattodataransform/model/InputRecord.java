package com.example.datattodataransform.model;

import com.azure.spring.data.cosmos.core.mapping.Container;
import com.azure.spring.data.cosmos.core.mapping.PartitionKey;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Container(containerName = "inputdata")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class InputRecord {
    @PartitionKey
    private String id;
    private String system;
    private String status;
    private String user;
    private String receivedTime;
    private String completedTime;
}
