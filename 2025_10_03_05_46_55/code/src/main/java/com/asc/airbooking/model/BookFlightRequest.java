package com.asc.airbooking.model;

import lombok.Data;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

/**
 * Request model for booking a flight.
 */
@Data
public class BookFlightRequest {
    @NotBlank(message = "Flight ID is required.")
    private String flightId;

    @NotNull(message = "Passenger information is required.")
    private Passenger passenger;

    @NotNull(message = "Payment information is required.")
    private Payment payment;

    @Data
    public static class Passenger {
        @NotBlank(message = "Passenger name is required.")
        private String name;
        @NotBlank(message = "Passenger email is required.")
        private String email;
    }

    @Data
    public static class Payment {
        @NotBlank(message = "Payment token is required.")
        private String token;
    }
}
