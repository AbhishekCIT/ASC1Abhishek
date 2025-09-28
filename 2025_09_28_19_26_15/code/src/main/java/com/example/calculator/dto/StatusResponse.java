package com.example.calculator.dto;

/**
 * DTO for status messages (e.g., add/clear history).
 */
public class StatusResponse {
    private String status;

    public StatusResponse() {}

    public StatusResponse(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
