package com.airbook.model;

import java.time.LocalDateTime;

/**
 * Model for NotificationLog entity
 */
public class NotificationLog {
    private String logId;
    private String userId;
    private String type;
    private String status;
    private String message;
    private LocalDateTime sentAt;

    // Getters and setters
    public String getLogId() { return logId; }
    public void setLogId(String logId) { this.logId = logId; }
    public String getUserId() { return userId; }
    public void setUserId(String userId) { this.userId = userId; }
    public String getType() { return type; }
    public void setType(String type) { this.type = type; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }
    public LocalDateTime getSentAt() { return sentAt; }
    public void setSentAt(LocalDateTime sentAt) { this.sentAt = sentAt; }
}
