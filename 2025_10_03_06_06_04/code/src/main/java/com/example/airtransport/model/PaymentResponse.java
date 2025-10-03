package com.example.airtransport.model;

import lombok.Builder;
import lombok.Data;

/**
 * Response model for payment processing.
 */
@Data
@Builder
public class PaymentResponse {
    private String paymentStatus;
    private String transactionId;
}
