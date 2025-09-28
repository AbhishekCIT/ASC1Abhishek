package com.example.airlinebooking.model;

/**
 * Response model for payment callback API.
 */
public class PaymentCallbackResponse {
    private String paymentStatus;

    public String getPaymentStatus() { return paymentStatus; }
    public void setPaymentStatus(String paymentStatus) { this.paymentStatus = paymentStatus; }
}
