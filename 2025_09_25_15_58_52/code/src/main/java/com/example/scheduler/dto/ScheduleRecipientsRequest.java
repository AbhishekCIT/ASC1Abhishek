package com.example.scheduler.dto;

import java.util.List;

/**
 * DTO for recipient removal request.
 */
public class ScheduleRecipientsRequest {
    private List<String> recipients;

    public List<String> getRecipients() { return recipients; }
    public void setRecipients(List<String> recipients) { this.recipients = recipients; }
}
