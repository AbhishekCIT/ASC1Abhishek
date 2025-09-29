package com.example.airtransport.model;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

import java.util.Map;

/**
 * Request model for booking a flight.
 */
@Data
public class BookingRequest {
    @NotEmpty(message = "Flight ID is required")
    private String flightId;
    @NotEmpty(message = "User ID is required")
    private String userId;
    private Map<String, Object> passengerInfo;
    private Map<String, Object> paymentInfo;
}
