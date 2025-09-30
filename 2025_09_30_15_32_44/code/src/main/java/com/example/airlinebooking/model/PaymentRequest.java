package com.example.airlinebooking.model;

/**
 * Request payload for processing a payment.
 */
public class PaymentRequest {
    private Double amount;
    private String paymentMethod;
    private PaymentInfo details;

    // Getters and setters
    public Double getAmount() { return amount; }
    public void setAmount(Double amount) { this.amount = amount; }
    public String getPaymentMethod() { return paymentMethod; }
    public void setPaymentMethod(String paymentMethod) { this.paymentMethod = paymentMethod; }
    public PaymentInfo getDetails() { return details; }
    public void setDetails(PaymentInfo details) { this.details = details; }
}
