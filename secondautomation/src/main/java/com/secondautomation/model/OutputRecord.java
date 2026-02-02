package com.secondautomation.model;

public class OutputRecord {
    private String recordId;
    private String id;
    private String service;
    private Sla sla;
    private String owner;
    private String receivedTime;
    private String completedTime;
    private long durationMinutes;
    private boolean breached;

    public OutputRecord(String recordId, String id, String service, Sla sla, String owner, String receivedTime, String completedTime, long durationMinutes, boolean breached) {
        this.recordId = recordId;
        this.id = id;
        this.service = service;
        this.sla = sla;
        this.owner = owner;
        this.receivedTime = receivedTime;
        this.completedTime = completedTime;
        this.durationMinutes = durationMinutes;
        this.breached = breached;
    }

    public String getRecordId() {
        return recordId;
    }
    public String getId() {
        return id;
    }
    public String getService() {
        return service;
    }
    public Sla getSla() {
        return sla;
    }
    public String getOwner() {
        return owner;
    }
    public String getReceivedTime() {
        return receivedTime;
    }
    public String getCompletedTime() {
        return completedTime;
    }
    public long getDurationMinutes() {
        return durationMinutes;
    }
    public boolean isBreached() {
        return breached;
    }

    public static class Sla {
        private String state;

        public Sla(String state) {
            this.state = state;
        }
        public String getState() {
            return state;
        }
    }
}