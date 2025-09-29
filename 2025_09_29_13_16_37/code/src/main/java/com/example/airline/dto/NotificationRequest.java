package com.example.airline.dto;

public class NotificationRequest {
    private String userId;
    private String type; // EMAIL or SMS
    private String message;

    // Getters and Setters
    public String getUserId() { return userId; }
    public void setUserId(String userId) { this.userId = userId; }
    public String getType() { return type; }
    public void setType(String type) { this.type = type; }
    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }
}