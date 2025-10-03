package com.example.airtransport.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;

/**
 * Request model for searching flights.
 */
@Data
public class FlightSearchRequest {
    @NotBlank(message = "Destination must not be empty")
    private String destination;

    @NotNull(message = "Date must not be null")
    private LocalDate date;

    @NotBlank(message = "Class must not be empty")
    private String flightClass; // Economy, Business, etc.
}
