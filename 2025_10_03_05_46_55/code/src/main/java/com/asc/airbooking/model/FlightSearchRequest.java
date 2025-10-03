package com.asc.airbooking.model;

import lombok.Data;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Future;
import java.time.LocalDate;

/**
 * Request model for searching flights.
 */
@Data
public class FlightSearchRequest {
    @NotBlank(message = "Origin airport code is required.")
    private String origin;

    @NotBlank(message = "Destination airport code is required.")
    private String destination;

    @Future(message = "Travel date must be in the future.")
    private LocalDate date;
}
