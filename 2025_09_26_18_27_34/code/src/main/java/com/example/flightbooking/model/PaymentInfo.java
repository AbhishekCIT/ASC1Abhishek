package com.example.flightbooking.model;

/**
 * Model for payment information (PCI DSS compliant fields).
 */
public class PaymentInfo {
    private String cardNumber;
    private String expiry;
    private String cvv;

    // Getters and Setters
    public String getCardNumber() { return cardNumber; }
    public void setCardNumber(String cardNumber) { this.cardNumber = cardNumber; }
    public String getExpiry() { return expiry; }
    public void setExpiry(String expiry) { this.expiry = expiry; }
    public String getCvv() { return cvv; }
    public void setCvv(String cvv) { this.cvv = cvv; }
}
