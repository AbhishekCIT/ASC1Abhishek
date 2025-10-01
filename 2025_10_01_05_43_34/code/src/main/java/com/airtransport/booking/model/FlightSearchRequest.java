package com.airtransport.booking.model;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import java.time.LocalDate;

@Data
public class FlightSearchRequest {
    @NotBlank(message = "Origin airport code is required")
    private String origin;

    @NotBlank(message = "Destination airport code is required")
    private String destination;

    @NotNull(message = "Travel date is required")
    @Future(message = "Travel date must be in the future")
    private LocalDate date;

    @NotNull(message = "Number of passengers is required")
    private Integer passengers;
}
