package com.example.datattodataransform.model;

import com.azure.spring.data.cosmos.core.mapping.Container;
import com.azure.spring.data.cosmos.core.mapping.PartitionKey;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Builder;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Container(containerName = "inputdata")
public class InputRecord {
    @PartitionKey
    private String id;
    private String system;
    private String status;
    private String receivedTime;
    private String completedTime;
    private String user;
}
