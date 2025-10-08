package com.example.airbooking.model;

/**
 * Response model for confirmation sending status.
 */
public class ConfirmationResponse {
    private String confirmationStatus;

    public ConfirmationResponse(String confirmationStatus) {
        this.confirmationStatus = confirmationStatus;
    }
    public ConfirmationResponse() {}

    public String getConfirmationStatus() { return confirmationStatus; }
    public void setConfirmationStatus(String confirmationStatus) { this.confirmationStatus = confirmationStatus; }
}
