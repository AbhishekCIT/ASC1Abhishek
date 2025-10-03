package com.example.airbooking.model;

import lombok.*;

/**
 * Response model for payment processing.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PaymentResponse {
    private Integer paymentId;
    private String status;
    private String timestamp;
}
