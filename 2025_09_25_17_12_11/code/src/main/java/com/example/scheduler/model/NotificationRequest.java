package com.example.scheduler.model;

/**
 * Model for notification API requests.
 */
public class NotificationRequest {
    private Long userId;
    private String message;

    public Long getUserId() { return userId; }
    public void setUserId(Long userId) { this.userId = userId; }
    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }
}
