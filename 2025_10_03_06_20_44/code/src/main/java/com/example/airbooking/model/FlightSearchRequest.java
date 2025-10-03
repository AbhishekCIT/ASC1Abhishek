package com.example.airbooking.model;

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
    @NotNull
    @Future(message = "Date must be in the future")
    private LocalDate date;

    @NotBlank
    private String from;

    @NotBlank
    private String to;

    @NotBlank
    private String flightClass;
}
