package com.example.flightbooking.model;

/**
 * Model for payment details (PCI DSS compliant fields only).
 */
public class PaymentDetails {
    private String cardNumber;
    private String expiry;
    private String cvv;
    private String cardHolderName;

    // Getters and setters
    public String getCardNumber() { return cardNumber; }
    public void setCardNumber(String cardNumber) { this.cardNumber = cardNumber; }
    public String getExpiry() { return expiry; }
    public void setExpiry(String expiry) { this.expiry = expiry; }
    public String getCvv() { return cvv; }
    public void setCvv(String cvv) { this.cvv = cvv; }
    public String getCardHolderName() { return cardHolderName; }
    public void setCardHolderName(String cardHolderName) { this.cardHolderName = cardHolderName; }
}
