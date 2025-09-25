package com.example.scheduler.model;

import java.util.List;

/**
 * Request model for creating a schedule.
 */
public class ScheduleRequest {
    private String reportId;
    private String frequency;
    private String time;
    private List<String> deliveryMethods;
    private List<String> recipients;

    // Getters and Setters
    public String getReportId() { return reportId; }
    public void setReportId(String reportId) { this.reportId = reportId; }
    public String getFrequency() { return frequency; }
    public void setFrequency(String frequency) { this.frequency = frequency; }
    public String getTime() { return time; }
    public void setTime(String time) { this.time = time; }
    public List<String> getDeliveryMethods() { return deliveryMethods; }
    public void setDeliveryMethods(List<String> deliveryMethods) { this.deliveryMethods = deliveryMethods; }
    public List<String> getRecipients() { return recipients; }
    public void setRecipients(List<String> recipients) { this.recipients = recipients; }
}
