package com.example.scheduler.model;

/**
 * Model for notification API responses.
 */
public class NotificationResponse {
    private String status;

    public NotificationResponse() {}
    public NotificationResponse(String status) { this.status = status; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}
