package com.secondautomation.model;

public class InputRecord {
    private String id;
    private String service;
    private String state;
    private String owner;
    private String receivedTime;
    private String completedTime;

    public InputRecord(String id, String service, String state, String owner, String receivedTime, String completedTime) {
        this.id = id;
        this.service = service;
        this.state = state;
        this.owner = owner;
        this.receivedTime = receivedTime;
        this.completedTime = completedTime;
    }

    public String getId() {
        return id;
    }
    public String getService() {
        return service;
    }
    public String getState() {
        return state;
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
}