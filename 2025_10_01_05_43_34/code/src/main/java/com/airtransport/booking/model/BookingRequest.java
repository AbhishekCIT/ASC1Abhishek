package com.airtransport.booking.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import java.util.List;

@Data
public class BookingRequest {
    @NotBlank(message = "Flight ID is required")
    private String flightId;

    @NotEmpty(message = "Passenger details are required")
    private List<PassengerDetail> passengerDetails;

    @NotNull(message = "Payment info is required")
    private PaymentInfo paymentInfo;

    @Data
    public static class PassengerDetail {
        @NotBlank(message = "Passenger name is required")
        private String name;
        @NotBlank(message = "Passenger email is required")
        private String email;
        // Add other fields as needed
    }

    @Data
    public static class PaymentInfo {
        @NotBlank(message = "Card number is required")
        private String cardNumber;
        @NotBlank(message = "Card expiry is required")
        private String expiry;
        @NotBlank(message = "CVV is required")
        private String cvv;
        @NotBlank(message = "Cardholder name is required")
        private String cardHolderName;
    }
}
