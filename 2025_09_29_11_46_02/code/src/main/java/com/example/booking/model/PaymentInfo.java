package com.example.booking.model;

/**
 * Model representing payment information.
 */
public class PaymentInfo {
    private String method; // e.g., card, paypal
    private String token;  // payment token

    public PaymentInfo() {}
    public PaymentInfo(String method, String token) {
        this.method = method;
        this.token = token;
    }

    public String getMethod() { return method; }
    public void setMethod(String method) { this.method = method; }

    public String getToken() { return token; }
    public void setToken(String token) { this.token = token; }

    @Override
    public String toString() {
        return "PaymentInfo{" +
                "method='" + method + '\'' +
                ", token='" + token + '\'' +
                '}';
    }
}
