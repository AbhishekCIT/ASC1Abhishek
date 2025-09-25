package com.example.scheduler.model;

import java.util.List;

/**
 * Response model for schedule creation and retrieval.
 */
public class ScheduleResponse {
    private String scheduleId;
    private String reportId;
    private String frequency;
    private String time;
    private List<String> deliveryMethods;
    private String status;

    public ScheduleResponse() {}

    public ScheduleResponse(String scheduleId, String status) {
        this.scheduleId = scheduleId;
        this.status = status;
    }

    public ScheduleResponse(String scheduleId, String reportId, String frequency, String time, List<String> deliveryMethods, String status) {
        this.scheduleId = scheduleId;
        this.reportId = reportId;
        this.frequency = frequency;
        this.time = time;
        this.deliveryMethods = deliveryMethods;
        this.status = status;
    }

    // Getters and Setters
    public String getScheduleId() { return scheduleId; }
    public void setScheduleId(String scheduleId) { this.scheduleId = scheduleId; }
    public String getReportId() { return reportId; }
    public void setReportId(String reportId) { this.reportId = reportId; }
    public String getFrequency() { return frequency; }
    public void setFrequency(String frequency) { this.frequency = frequency; }
    public String getTime() { return time; }
    public void setTime(String time) { this.time = time; }
    public List<String> getDeliveryMethods() { return deliveryMethods; }
    public void setDeliveryMethods(List<String> deliveryMethods) { this.deliveryMethods = deliveryMethods; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}
