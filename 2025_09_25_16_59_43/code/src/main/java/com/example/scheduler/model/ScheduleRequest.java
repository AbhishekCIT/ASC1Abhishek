package com.example.scheduler.model;

import java.util.List;

/**
 * Model for schedule creation/edit request
 */
public class ScheduleRequest {
    private Long reportId;
    private String frequency;
    private String time;
    private List<String> recipients;

    public ScheduleRequest() {}

    public ScheduleRequest(Long reportId, String frequency, String time, List<String> recipients) {
        this.reportId = reportId;
        this.frequency = frequency;
        this.time = time;
        this.recipients = recipients;
    }

    public Long getReportId() {
        return reportId;
    }

    public void setReportId(Long reportId) {
        this.reportId = reportId;
    }

    public String getFrequency() {
        return frequency;
    }

    public void setFrequency(String frequency) {
        this.frequency = frequency;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public List<String> getRecipients() {
        return recipients;
    }

    public void setRecipients(List<String> recipients) {
        this.recipients = recipients;
    }
}
