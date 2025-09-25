package com.example.scheduler.dto;

import java.time.LocalDateTime;
import java.util.List;

/**
 * DTO for schedule list response.
 */
public class ScheduleListResponse {
    private Long scheduleId;
    private Long reportId;
    private String frequency;
    private List<String> recipients;
    private String deliveryMethod;
    private LocalDateTime nextRun;

    public Long getScheduleId() { return scheduleId; }
    public void setScheduleId(Long scheduleId) { this.scheduleId = scheduleId; }

    public Long getReportId() { return reportId; }
    public void setReportId(Long reportId) { this.reportId = reportId; }

    public String getFrequency() { return frequency; }
    public void setFrequency(String frequency) { this.frequency = frequency; }

    public List<String> getRecipients() { return recipients; }
    public void setRecipients(List<String> recipients) { this.recipients = recipients; }

    public String getDeliveryMethod() { return deliveryMethod; }
    public void setDeliveryMethod(String deliveryMethod) { this.deliveryMethod = deliveryMethod; }

    public LocalDateTime getNextRun() { return nextRun; }
    public void setNextRun(LocalDateTime nextRun) { this.nextRun = nextRun; }
}
