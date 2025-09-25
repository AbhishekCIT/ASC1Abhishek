package com.example.airbooking.model;

import java.math.BigDecimal;

/**
 * PaymentInfo holds payment details for booking.
 */
public class PaymentInfo {
    private String cardNumber;
    private String cardHolder;
    private String expiry;
    private String cvv;
    private BigDecimal amount;

    public String getCardNumber() { return cardNumber; }
    public void setCardNumber(String cardNumber) { this.cardNumber = cardNumber; }
    public String getCardHolder() { return cardHolder; }
    public void setCardHolder(String cardHolder) { this.cardHolder = cardHolder; }
    public String getExpiry() { return expiry; }
    public void setExpiry(String expiry) { this.expiry = expiry; }
    public String getCvv() { return cvv; }
    public void setCvv(String cvv) { this.cvv = cvv; }
    public BigDecimal getAmount() { return amount; }
    public void setAmount(BigDecimal amount) { this.amount = amount; }
}
