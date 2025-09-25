package com.example.scheduler.dto;

import java.time.LocalDateTime;
import java.util.List;

/**
 * DTO for schedule creation request.
 */
public class ScheduleCreateRequest {
    private Long reportId;
    private String frequency;
    private List<String> recipients;
    private String deliveryMethod;
    private LocalDateTime startDate;

    public Long getReportId() { return reportId; }
    public void setReportId(Long reportId) { this.reportId = reportId; }

    public String getFrequency() { return frequency; }
    public void setFrequency(String frequency) { this.frequency = frequency; }

    public List<String> getRecipients() { return recipients; }
    public void setRecipients(List<String> recipients) { this.recipients = recipients; }

    public String getDeliveryMethod() { return deliveryMethod; }
    public void setDeliveryMethod(String deliveryMethod) { this.deliveryMethod = deliveryMethod; }

    public LocalDateTime getStartDate() { return startDate; }
    public void setStartDate(LocalDateTime startDate) { this.startDate = startDate; }
}
