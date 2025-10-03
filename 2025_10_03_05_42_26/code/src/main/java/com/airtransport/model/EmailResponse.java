package com.airtransport.model;

/**
 * Model for email notification response payload.
 */
public class EmailResponse {
    private String notificationStatus;

    public EmailResponse() {}
    public EmailResponse(String notificationStatus) {
        this.notificationStatus = notificationStatus;
    }

    public String getNotificationStatus() { return notificationStatus; }
    public void setNotificationStatus(String notificationStatus) { this.notificationStatus = notificationStatus; }
}
