package com.example.scheduling.model;

import java.util.List;

/**
 * Model class for schedule response payload.
 */
public class ScheduleResponse {
    private Long id;
    private String status;
    private String frequency;
    private String time;
    private String reportType;
    private List<String> recipients;

    // Constructor for status responses (created, updated, deleted)
    public ScheduleResponse(Long id, String status) {
        this.id = id;
        this.status = status;
    }

    // Constructor for schedule details
    public ScheduleResponse(Long id, String frequency, String time, String reportType, List<String> recipients) {
        this.id = id;
        this.frequency = frequency;
        this.time = time;
        this.reportType = reportType;
        this.recipients = recipients;
        this.status = "success";
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public String getFrequency() { return frequency; }
    public void setFrequency(String frequency) { this.frequency = frequency; }

    public String getTime() { return time; }
    public void setTime(String time) { this.time = time; }

    public String getReportType() { return reportType; }
    public void setReportType(String reportType) { this.reportType = reportType; }

    public List<String> getRecipients() { return recipients; }
    public void setRecipients(List<String> recipients) { this.recipients = recipients; }
}
