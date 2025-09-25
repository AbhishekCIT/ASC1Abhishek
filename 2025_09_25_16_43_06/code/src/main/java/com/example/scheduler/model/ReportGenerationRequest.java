package com.example.scheduler.model;

/**
 * Model for report generation API request.
 */
public class ReportGenerationRequest {
    private Long scheduleId;

    // Getters and Setters
    public Long getScheduleId() { return scheduleId; }
    public void setScheduleId(Long scheduleId) { this.scheduleId = scheduleId; }
}
