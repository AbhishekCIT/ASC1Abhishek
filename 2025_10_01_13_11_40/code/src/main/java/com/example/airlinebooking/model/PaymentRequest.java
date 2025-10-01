package com.example.airlinebooking.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.Map;

/**
 * Request model for payment processing.
 */
@Data
public class PaymentRequest {
    @NotBlank(message = "Booking ID is required")
    private String bookingId;

    @NotNull(message = "Payment details are required")
    private Map<String, Object> paymentDetails;
}
