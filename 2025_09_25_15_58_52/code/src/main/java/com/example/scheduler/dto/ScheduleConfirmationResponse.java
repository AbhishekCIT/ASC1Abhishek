package com.example.scheduler.dto;

/**
 * DTO for schedule confirmation response.
 */
public class ScheduleConfirmationResponse {
    private Long scheduleId;
    private String status;
    private String confirmation;

    public Long getScheduleId() { return scheduleId; }
    public void setScheduleId(Long scheduleId) { this.scheduleId = scheduleId; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public String getConfirmation() { return confirmation; }
    public void setConfirmation(String confirmation) { this.confirmation = confirmation; }
}
