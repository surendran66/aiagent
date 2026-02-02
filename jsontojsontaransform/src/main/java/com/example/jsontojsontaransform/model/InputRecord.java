package com.example.jsontojsontaransform.model;

import com.azure.spring.data.cosmos.core.mapping.Container;
import com.azure.spring.data.cosmos.core.mapping.PartitionKey;
import lombok.Data;

@Data
@Container(containerName = "inputdata")
public class InputRecord {
    private String id;
    private String system;
    private String status;
    private String receivedTime; // ISO_LOCAL_DATE_TIME
    private String completedTime; // ISO_LOCAL_DATE_TIME
    private String user;
    
    @PartitionKey
    public String getId() { return id; }
}
