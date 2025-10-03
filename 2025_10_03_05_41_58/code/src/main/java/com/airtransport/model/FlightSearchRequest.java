package com.airtransport.model;

import lombok.Data;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;

/**
 * Request model for searching flights.
 */
@Data
public class FlightSearchRequest {
    @NotNull(message = "Date is required and must be valid")
    private LocalDate date;

    @NotBlank(message = "Destination is required")
    private String destination;

    private String airline;
}
