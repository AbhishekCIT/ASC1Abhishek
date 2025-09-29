package com.example.airtransport.model;

/**
 * Model representing payment information for a booking.
 */
public class PaymentInfo {
    private String cardNumber;
    private String cardHolder;
    private String expiryDate;
    private String cvv;
    private double amount;

    public String getCardNumber() { return cardNumber; }
    public void setCardNumber(String cardNumber) { this.cardNumber = cardNumber; }
    public String getCardHolder() { return cardHolder; }
    public void setCardHolder(String cardHolder) { this.cardHolder = cardHolder; }
    public String getExpiryDate() { return expiryDate; }
    public void setExpiryDate(String expiryDate) { this.expiryDate = expiryDate; }
    public String getCvv() { return cvv; }
    public void setCvv(String cvv) { this.cvv = cvv; }
    public double getAmount() { return amount; }
    public void setAmount(double amount) { this.amount = amount; }
}
