package com.example.airbooking.model;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

/**
 * Request model for notification subscription API.
 */
public class NotificationSubscribeRequest {
    @NotBlank(message = "Flight number is required")
    private String flightNumber;
    @Email(message = "Invalid email format")
    private String email;

    public String getFlightNumber() { return flightNumber; }
    public void setFlightNumber(String flightNumber) { this.flightNumber = flightNumber; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
}
