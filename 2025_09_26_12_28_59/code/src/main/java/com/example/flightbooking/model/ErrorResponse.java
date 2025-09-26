package com.example.flightbooking.model;

/**
 * Model for error response payloads.
 */
public class ErrorResponse {
    private String error;

    public ErrorResponse(String error) {
        this.error = error;
    }

    public String getError() { return error; }
    public void setError(String error) { this.error = error; }
}
