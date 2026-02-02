package com.firstautomation.model;

public class InputRecord {
    private String id;
    private String system;
    private String state;
    private String start;
    private String end;
    private String owner;

    public InputRecord() {}

    public InputRecord(String id, String system, String state, String start, String end, String owner) {
        this.id = id;
        this.system = system;
        this.state = state;
        this.start = start;
        this.end = end;
        this.owner = owner;
    }

    public String getId() {
        return id;
    }
    public String getSystem() {
        return system;
    }
    public String getState() {
        return state;
    }
    public String getStart() {
        return start;
    }
    public String getEnd() {
        return end;
    }
    public String getOwner() {
        return owner;
    }
}
