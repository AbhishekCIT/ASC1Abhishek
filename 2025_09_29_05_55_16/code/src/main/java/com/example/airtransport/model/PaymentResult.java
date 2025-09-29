package com.example.airtransport.model;

/**
 * Model representing the result of a payment transaction.
 */
public class PaymentResult {
    private boolean success;
    private String message;
    private double amount;

    public PaymentResult() {}

    public PaymentResult(boolean success, String message) {
        this.success = success;
        this.message = message;
    }

    public boolean isSuccess() { return success; }
    public void setSuccess(boolean success) { this.success = success; }
    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }
    public double getAmount() { return amount; }
    public void setAmount(double amount) { this.amount = amount; }
}
