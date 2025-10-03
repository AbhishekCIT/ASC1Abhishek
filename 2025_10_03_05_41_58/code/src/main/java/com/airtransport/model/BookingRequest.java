package com.airtransport.model;

import lombok.Data;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

/**
 * Request model for booking a flight.
 */
@Data
public class BookingRequest {
    @NotBlank(message = "Flight ID is required")
    private String flightId;

    @NotNull(message = "Passenger details incomplete")
    private PassengerDetails passengerDetails;

    @NotNull(message = "Payment info is required")
    private PaymentInfo paymentInfo;

    @Data
    public static class PassengerDetails {
        @NotBlank(message = "Name is required")
        private String name;
        @NotNull(message = "Age is required")
        private Integer age;
    }

    @Data
    public static class PaymentInfo {
        @NotBlank(message = "Payment method is required")
        private String method;
        @NotBlank(message = "Card number is required")
        private String cardNumber;
    }
}
