package com.example.airbooking.model;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import java.util.List;

/**
 * Request model for booking a flight.
 */
@Data
public class BookingRequest {
    @NotNull
    private Integer flightId;
    @NotNull
    private Integer userId;
    @NotNull
    private List<PassengerDetail> passengerDetails;
    @NotNull
    private PaymentInfo paymentInfo;

    /**
     * Inner class for passenger details.
     */
    @Data
    public static class PassengerDetail {
        @NotNull
        private String name;
        @NotNull
        private String passportNumber;
    }

    /**
     * Inner class for payment information.
     */
    @Data
    public static class PaymentInfo {
        @NotNull
        private String cardNumber;
        @NotNull
        private String cardHolder;
        @NotNull
        private String expiry;
        @NotNull
        private String cvv;
    }
}
