package com.example.airbooking.model;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

/**
 * Request model for searching flights.
 */
public class FlightSearchRequest {
    @NotBlank(message = "Origin is required")
    @Pattern(regexp = "^[A-Z]{3}$", message = "Invalid IATA code for origin")
    private String origin;

    @NotBlank(message = "Destination is required")
    @Pattern(regexp = "^[A-Z]{3}$", message = "Invalid IATA code for destination")
    private String destination;

    @NotNull(message = "Date is required")
    private LocalDate date;

    // Getters and setters
    public String getOrigin() { return origin; }
    public void setOrigin(String origin) { this.origin = origin; }
    public String getDestination() { return destination; }
    public void setDestination(String destination) { this.destination = destination; }
    public LocalDate getDate() { return date; }
    public void setDate(LocalDate date) { this.date = date; }
}
