package com.example.airtransport.model;

/**
 * Model representing payment information for booking.
 */
public class PaymentInfo {
    private String cardNumber;
    private String expiry;
    private String cvv;

    // Getters and setters
    public String getCardNumber() { return cardNumber; }
    public void setCardNumber(String cardNumber) { this.cardNumber = cardNumber; }
    public String getExpiry() { return expiry; }
    public void setExpiry(String expiry) { this.expiry = expiry; }
    public String getCvv() { return cvv; }
    public void setCvv(String cvv) { this.cvv = cvv; }
}
