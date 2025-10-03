package com.airtransport.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Response model for processing a payment.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PaymentResponse {
    private String paymentStatus;
    private String transactionId;
}
