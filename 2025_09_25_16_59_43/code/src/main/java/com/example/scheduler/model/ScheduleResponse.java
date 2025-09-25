package com.example.scheduler.model;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Model for schedule response
 */
public class ScheduleResponse {
    private Long scheduleId;
    private Long reportId;
    private String frequency;
    private String time;
    private List<String> recipients;
    private LocalDateTime nextRun;
    private String status;

    public ScheduleResponse() {}

    public ScheduleResponse(Long scheduleId, String status) {
        this.scheduleId = scheduleId;
        this.status = status;
    }

    public ScheduleResponse(Long scheduleId, Long reportId, String frequency, String time, List<String> recipients, LocalDateTime nextRun) {
        this.scheduleId = scheduleId;
        this.reportId = reportId;
        this.frequency = frequency;
        this.time = time;
        this.recipients = recipients;
        this.nextRun = nextRun;
    }

    public Long getScheduleId() {
        return scheduleId;
    }

    public void setScheduleId(Long scheduleId) {
        this.scheduleId = scheduleId;
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

    public LocalDateTime getNextRun() {
        return nextRun;
    }

    public void setNextRun(LocalDateTime nextRun) {
        this.nextRun = nextRun;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
