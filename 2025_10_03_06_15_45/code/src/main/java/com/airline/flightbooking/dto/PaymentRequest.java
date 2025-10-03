package com.airline.flightbooking.dto;

import lombok.Data;

/**
 * Request DTO for processing payment.
 */
@Data
public class PaymentRequest {
    private String bookingId;
    private double amount;
    private String paymentMethod;
    private Object details; // Placeholder for card or other payment details
}
