package com.example.scheduler.dto;

import java.util.List;

/**
 * DTO for recipients update response.
 */
public class ScheduleRecipientsResponse {
    private Long scheduleId;
    private List<String> recipients;
    private String status;

    public Long getScheduleId() { return scheduleId; }
    public void setScheduleId(Long scheduleId) { this.scheduleId = scheduleId; }

    public List<String> getRecipients() { return recipients; }
    public void setRecipients(List<String> recipients) { this.recipients = recipients; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}
