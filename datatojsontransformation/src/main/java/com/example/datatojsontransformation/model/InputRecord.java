package com.example.datatojsontransformation.model;

import com.azure.spring.data.cosmos.core.mapping.Container;
import com.azure.spring.data.cosmos.core.mapping.PartitionKey;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.data.annotation.Id;

@Container(containerName = "inputdata")
public class InputRecord {
    @Id
    @PartitionKey
    private String id;
    private String status;
    private String receivedTime;
    private String completedTime;
    private String system;
    private String user;

    public InputRecord() {}

    public InputRecord(String id, String status, String receivedTime, String completedTime, String system, String user) {
        this.id = id;
        this.status = status;
        this.receivedTime = receivedTime;
        this.completedTime = completedTime;
        this.system = system;
        this.user = user;
    }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    public String getReceivedTime() { return receivedTime; }
    public void setReceivedTime(String receivedTime) { this.receivedTime = receivedTime; }
    public String getCompletedTime() { return completedTime; }
    public void setCompletedTime(String completedTime) { this.completedTime = completedTime; }
    public String getSystem() { return system; }
    public void setSystem(String system) { this.system = system; }
    public String getUser() { return user; }
    public void setUser(String user) { this.user = user; }
}
