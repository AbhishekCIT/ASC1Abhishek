package com.example.airbooking.model;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * Request model for payment processing.
 */
@Data
public class PaymentRequest {
    @NotNull
    private Integer bookingId;
    @NotNull
    private String paymentMethod; // e.g., CARD, PAYPAL
    @NotNull
    private CardDetails cardDetails;

    @Data
    public static class CardDetails {
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
