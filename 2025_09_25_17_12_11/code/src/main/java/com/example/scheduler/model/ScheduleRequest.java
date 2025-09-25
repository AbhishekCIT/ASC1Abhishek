package com.example.scheduler.model;

import java.util.List;

/**
 * Model for schedule creation and update requests.
 */
public class ScheduleRequest {
    private Long reportId;
    private String frequency;
    private String time;
    private List<String> recipients;

    public Long getReportId() { return reportId; }
    public void setReportId(Long reportId) { this.reportId = reportId; }
    public String getFrequency() { return frequency; }
    public void setFrequency(String frequency) { this.frequency = frequency; }
    public String getTime() { return time; }
    public void setTime(String time) { this.time = time; }
    public List<String> getRecipients() { return recipients; }
    public void setRecipients(List<String> recipients) { this.recipients = recipients; }
}
