package com.example.airbooking.model;

/**
 * Response model for notification subscription API.
 */
public class NotificationSubscribeResponse {
    private String subscriptionId;
    private String status;

    public String getSubscriptionId() { return subscriptionId; }
    public void setSubscriptionId(String subscriptionId) { this.subscriptionId = subscriptionId; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}
