package com.example.airtransport.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * Request model for booking a flight.
 */
@Data
public class BookingRequest {
    @NotBlank(message = "Flight ID must not be empty")
    private String flightId;

    @NotBlank(message = "User ID must not be empty")
    private String userId;

    @NotNull(message = "Payment info must not be null")
    private PaymentInfo paymentInfo;
}
