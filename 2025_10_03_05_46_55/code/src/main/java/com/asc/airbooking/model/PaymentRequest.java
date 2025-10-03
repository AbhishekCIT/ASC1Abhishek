package com.asc.airbooking.model;

import lombok.Data;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.NotBlank;

/**
 * Request model for payment processing.
 */
@Data
public class PaymentRequest {
    @NotNull(message = "Amount is required.")
    private Double amount;

    @NotBlank(message = "Payment token is required.")
    private String token;
}
