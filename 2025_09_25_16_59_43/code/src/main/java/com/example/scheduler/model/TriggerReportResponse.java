package com.example.scheduler.model;

import java.time.LocalDateTime;

/**
 * Model for trigger report API response
 */
public class TriggerReportResponse {
    private String deliveryStatus;
    private LocalDateTime deliveryTime;

    public TriggerReportResponse() {}

    public TriggerReportResponse(String deliveryStatus, LocalDateTime deliveryTime) {
        this.deliveryStatus = deliveryStatus;
        this.deliveryTime = deliveryTime;
    }

    public String getDeliveryStatus() {
        return deliveryStatus;
    }

    public void setDeliveryStatus(String deliveryStatus) {
        this.deliveryStatus = deliveryStatus;
    }

    public LocalDateTime getDeliveryTime() {
        return deliveryTime;
    }

    public void setDeliveryTime(LocalDateTime deliveryTime) {
        this.deliveryTime = deliveryTime;
    }
}
