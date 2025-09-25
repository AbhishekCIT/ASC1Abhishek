package com.example.scheduler.model;

import java.util.Date;

/**
 * Response model for manual report trigger.
 */
public class TriggerReportResponse {
    private String deliveryStatus;
    private Date timestamp;

    public TriggerReportResponse() {}
    public TriggerReportResponse(String deliveryStatus, Date timestamp) {
        this.deliveryStatus = deliveryStatus;
        this.timestamp = timestamp;
    }

    // Getters and Setters
    public String getDeliveryStatus() { return deliveryStatus; }
    public void setDeliveryStatus(String deliveryStatus) { this.deliveryStatus = deliveryStatus; }
    public Date getTimestamp() { return timestamp; }
    public void setTimestamp(Date timestamp) { this.timestamp = timestamp; }
}
