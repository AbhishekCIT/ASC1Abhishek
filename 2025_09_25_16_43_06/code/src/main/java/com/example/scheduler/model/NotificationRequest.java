package com.example.scheduler.model;

import java.util.List;

/**
 * Model for notification API request.
 */
public class NotificationRequest {
    private Long scheduleId;
    private String status;
    private List<String> recipients;

    // Getters and Setters
    public Long getScheduleId() { return scheduleId; }
    public void setScheduleId(Long scheduleId) { this.scheduleId = scheduleId; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    public List<String> getRecipients() { return recipients; }
    public void setRecipients(List<String> recipients) { this.recipients = recipients; }
}
