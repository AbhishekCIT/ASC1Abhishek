package com.example.scheduling.model;

import java.util.List;

/**
 * Model class for schedule creation/edit request payload.
 */
public class ScheduleRequest {
    private String frequency;
    private String time;
    private String reportType;
    private List<String> recipients;
    private Long createdBy;

    public String getFrequency() { return frequency; }
    public void setFrequency(String frequency) { this.frequency = frequency; }

    public String getTime() { return time; }
    public void setTime(String time) { this.time = time; }

    public String getReportType() { return reportType; }
    public void setReportType(String reportType) { this.reportType = reportType; }

    public List<String> getRecipients() { return recipients; }
    public void setRecipients(List<String> recipients) { this.recipients = recipients; }

    public Long getCreatedBy() { return createdBy; }
    public void setCreatedBy(Long createdBy) { this.createdBy = createdBy; }
}
