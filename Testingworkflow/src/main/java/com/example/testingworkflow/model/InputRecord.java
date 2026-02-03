package com.example.testingworkflow.model;

public class InputRecord {
    private String id;
    private String system;
    private String status;
    private String receivedTime;
    private String completedTime;
    private String user;

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public String getSystem() { return system; }
    public void setSystem(String system) { this.system = system; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    public String getReceivedTime() { return receivedTime; }
    public void setReceivedTime(String receivedTime) { this.receivedTime = receivedTime; }
    public String getCompletedTime() { return completedTime; }
    public void setCompletedTime(String completedTime) { this.completedTime = completedTime; }
    public String getUser() { return user; }
    public void setUser(String user) { this.user = user; }
}
