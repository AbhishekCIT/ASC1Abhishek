package com.example.airtransport.model;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

/**
 * Request model for searching flights.
 */
@Data
public class FlightSearchRequest {
    @NotEmpty(message = "Origin cannot be empty")
    private String origin;
    @NotEmpty(message = "Destination cannot be empty")
    private String destination;
    @NotEmpty(message = "Date must be provided")
    private String date; // yyyy-MM-dd
}
