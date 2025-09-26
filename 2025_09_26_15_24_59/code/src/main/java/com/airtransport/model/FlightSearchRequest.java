package com.airtransport.model;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * Request model for searching flights.
 */
@Data
public class FlightSearchRequest {
    @NotBlank(message = "Origin airport code is required")
    private String origin;

    @NotBlank(message = "Destination airport code is required")
    private String destination;

    @NotBlank(message = "Travel date is required")
    private String date; // ISO format (yyyy-MM-dd)

    @NotNull
    @Min(value = 1, message = "At least one passenger is required")
    private Integer passengers;
}
