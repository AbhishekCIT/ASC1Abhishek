package com.asc.airbooking.model;

import lombok.Data;

/**
 * Response model for payment processing.
 */
@Data
public class PaymentResponse {
    private String paymentStatus;
    private String transactionId;
}
