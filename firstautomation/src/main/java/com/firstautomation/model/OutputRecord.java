package com.firstautomation.model;

public class OutputRecord {
    private String recordId;
    private String service;
    private Sla sla;
    private int durationMinutes;
    private boolean breached;
    private String owner;

    public OutputRecord() {}

    public OutputRecord(String recordId, String service, Sla sla, int durationMinutes, boolean breached, String owner) {
        this.recordId = recordId;
        this.service = service;
        this.sla = sla;
        this.durationMinutes = durationMinutes;
        this.breached = breached;
        this.owner = owner;
    }

    public String getRecordId() {
        return recordId;
    }
    public String getService() {
        return service;
    }
    public Sla getSla() {
        return sla;
    }
    public int getDurationMinutes() {
        return durationMinutes;
    }
    public boolean isBreached() {
        return breached;
    }
    public String getOwner() {
        return owner;
    }

    public static class Sla {
        private String state;
        public Sla() {}
        public Sla(String state) { this.state = state; }
        public String getState() { return state; }
    }
}
