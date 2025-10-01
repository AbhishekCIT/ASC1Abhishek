package com.example.airlinebooking.model;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;

/**
 * Request model for searching flights.
 */
@Data
public class FlightSearchRequest {
    @NotBlank(message = "Origin cannot be empty")
    private String origin;

    @NotBlank(message = "Destination cannot be empty")
    private String destination;

    @NotNull(message = "Date is required")
    @Future(message = "Date must be in the future")
    private LocalDate date;
}
