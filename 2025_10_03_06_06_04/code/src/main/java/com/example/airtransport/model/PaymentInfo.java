package com.example.airtransport.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * Model representing payment information for a booking.
 */
@Data
public class PaymentInfo {
    @NotBlank(message = "Card number must not be empty")
    private String cardNumber;

    @NotBlank(message = "Card holder name must not be empty")
    private String cardHolder;

    @NotBlank(message = "Expiry date must not be empty")
    private String expiryDate; // MM/YY

    @NotBlank(message = "CVV must not be empty")
    private String cvv;

    @NotNull(message = "Amount must not be null")
    private Double amount;

    @NotBlank(message = "Currency must not be empty")
    private String currency;
}
