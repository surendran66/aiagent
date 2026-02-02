package com.example.datattodataransform.model;

import com.azure.spring.data.cosmos.core.mapping.Container;
import com.azure.spring.data.cosmos.core.mapping.PartitionKey;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Container(containerName = "inputdata")
public class InputRecord {
    private String id;
    private String system;
    private String status;
    private String user;
    private String receivedTime;
    private String completedTime;
    @PartitionKey
    public String getId() {
        return id;
    }
}
