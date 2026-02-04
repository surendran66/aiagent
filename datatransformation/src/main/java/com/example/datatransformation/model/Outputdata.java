package com.example.datatransformation.model;

import com.azure.spring.data.cosmos.core.mapping.Container;
import com.azure.spring.data.cosmos.core.mapping.PartitionKey;
import org.springframework.data.annotation.Id;

@Container(containerName = "outputdata")
public class Outputdata {
    @Id
    @PartitionKey
    private String recordId;
    private String id;
    private String service;
    private Sla sla;
    private String receivedTime;
    private String completedTime;
    private double durationMinutes;
    private boolean breached;
    private String Owner;

    public Outputdata() {}

    public Outputdata(String recordId, String id, String service, Sla sla, String receivedTime, String completedTime, double durationMinutes, boolean breached, String Owner) {
        this.recordId = recordId;
        this.id = id;
        this.service = service;
        this.sla = sla;
        this.receivedTime = receivedTime;
        this.completedTime = completedTime;
        this.durationMinutes = durationMinutes;
        this.breached = breached;
        this.Owner = Owner;
    }

    public String getRecordId() { return recordId; }
    public void setRecordId(String recordId) { this.recordId = recordId; }
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public String getService() { return service; }
    public void setService(String service) { this.service = service; }
    public Sla getSla() { return sla; }
    public void setSla(Sla sla) { this.sla = sla; }
    public String getReceivedTime() { return receivedTime; }
    public void setReceivedTime(String receivedTime) { this.receivedTime = receivedTime; }
    public String getCompletedTime() { return completedTime; }
    public void setCompletedTime(String completedTime) { this.completedTime = completedTime; }
    public double getDurationMinutes() { return durationMinutes; }
    public void setDurationMinutes(double durationMinutes) { this.durationMinutes = durationMinutes; }
    public boolean isBreached() { return breached; }
    public void setBreached(boolean breached) { this.breached = breached; }
    public String getOwner() { return Owner; }
    public void setOwner(String Owner) { this.Owner = Owner; }

    public static class Sla {
        private String state;
        public Sla() {}
        public Sla(String state) { this.state = state; }
        public String getState() { return state; }
        public void setState(String state) { this.state = state; }
    }
}
