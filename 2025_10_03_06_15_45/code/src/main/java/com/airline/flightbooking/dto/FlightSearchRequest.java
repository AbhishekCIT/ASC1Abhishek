package com.airline.flightbooking.dto;

import lombok.Data;

/**
 * Request DTO for searching flights.
 */
@Data
public class FlightSearchRequest {
    private String origin;
    private String destination;
    private String date; // ISO format (yyyy-MM-dd)
}
