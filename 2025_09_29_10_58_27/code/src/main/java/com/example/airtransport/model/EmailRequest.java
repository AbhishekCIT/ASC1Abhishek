package com.example.airtransport.model;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

/**
 * Request model for sending confirmation email.
 */
@Data
public class EmailRequest {
    @NotEmpty(message = "Booking ID is required")
    private String bookingId;
    @Email(message = "Invalid email address")
    @NotEmpty(message = "Email is required")
    private String email;
}
