package com.example.flightbooking.model;

/**
 * Model for flight status update (e.g., delay, gate change).
 */
public class FlightStatusUpdate {
    private String type;
    private String message;

    // Getters and Setters
    public String getType() { return type; }
    public void setType(String type) { this.type = type; }
    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }
}
