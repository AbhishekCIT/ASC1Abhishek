package com.example.airtransport.model;

/**
 * Model representing payment information for booking.
 */
public class PaymentInfo {
    private String method;
    private double amount;
    private String cardNumber;
    private String cardExpiry;
    private String cardCvv;
    // Add more fields as needed for payment gateway

    public String getMethod() { return method; }
    public void setMethod(String method) { this.method = method; }
    public double getAmount() { return amount; }
    public void setAmount(double amount) { this.amount = amount; }
    public String getCardNumber() { return cardNumber; }
    public void setCardNumber(String cardNumber) { this.cardNumber = cardNumber; }
    public String getCardExpiry() { return cardExpiry; }
    public void setCardExpiry(String cardExpiry) { this.cardExpiry = cardExpiry; }
    public String getCardCvv() { return cardCvv; }
    public void setCardCvv(String cardCvv) { this.cardCvv = cardCvv; }
}
