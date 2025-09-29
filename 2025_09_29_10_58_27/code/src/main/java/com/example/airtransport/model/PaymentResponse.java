package com.example.airtransport.model;

import lombok.Data;

/**
 * Response model for payment processing.
 */
@Data
public class PaymentResponse {
    private String paymentId;
    private String status;
}
