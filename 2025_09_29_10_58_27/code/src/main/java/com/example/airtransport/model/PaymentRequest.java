package com.example.airtransport.model;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

import java.util.Map;

/**
 * Request model for processing a payment.
 */
@Data
public class PaymentRequest {
    @NotEmpty(message = "Booking ID is required")
    private String bookingId;
    private Map<String, Object> paymentInfo;
}
