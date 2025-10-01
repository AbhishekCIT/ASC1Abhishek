package com.example.airlinebooking.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.Map;

/**
 * Request model for booking a ticket.
 */
@Data
public class BookTicketRequest {
    @NotBlank(message = "Flight ID is required")
    private String flightId;

    @NotBlank(message = "User ID is required")
    private String userId;

    @NotBlank(message = "Seat is required")
    private String seat;

    @NotNull(message = "Payment details are required")
    private Map<String, Object> payment;
}
