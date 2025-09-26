package com.airtransport.model;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

/**
 * Model for payment information.
 */
@Data
public class PaymentInfo {
    @NotBlank(message = "Card number is required")
    private String cardNumber;

    @NotBlank(message = "Card expiry is required")
    private String expiry;

    @NotBlank(message = "CVV is required")
    private String cvv;

    @NotBlank(message = "Cardholder name is required")
    private String cardholderName;
}
