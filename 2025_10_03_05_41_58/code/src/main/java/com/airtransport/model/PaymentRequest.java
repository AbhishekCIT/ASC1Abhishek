package com.airtransport.model;

import lombok.Data;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;

/**
 * Request model for processing a payment.
 */
@Data
public class PaymentRequest {
    @NotBlank(message = "Booking ID is required")
    private String bookingId;

    @NotNull(message = "Amount is required")
    private BigDecimal amount;

    @NotBlank(message = "Payment method is required")
    private String paymentMethod;
}
