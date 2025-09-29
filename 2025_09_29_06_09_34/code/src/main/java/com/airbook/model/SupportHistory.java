package com.airbook.model;

import java.time.LocalDateTime;

/**
 * Model for SupportHistory entity
 */
public class SupportHistory {
    private String historyId;
    private String requestId;
    private String action;
    private String performedBy;
    private LocalDateTime actionAt;

    // Getters and setters
    public String getHistoryId() { return historyId; }
    public void setHistoryId(String historyId) { this.historyId = historyId; }
    public String getRequestId() { return requestId; }
    public void setRequestId(String requestId) { this.requestId = requestId; }
    public String getAction() { return action; }
    public void setAction(String action) { this.action = action; }
    public String getPerformedBy() { return performedBy; }
    public void setPerformedBy(String performedBy) { this.performedBy = performedBy; }
    public LocalDateTime getActionAt() { return actionAt; }
    public void setActionAt(LocalDateTime actionAt) { this.actionAt = actionAt; }
}
