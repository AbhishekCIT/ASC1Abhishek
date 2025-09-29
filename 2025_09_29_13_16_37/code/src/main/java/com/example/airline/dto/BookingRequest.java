package com.example.airline.dto;

public class BookingRequest {
    private String flightId;
    private String userId;
    private PaymentDetails payment;

    // Getters and Setters
    public String getFlightId() { return flightId; }
    public void setFlightId(String flightId) { this.flightId = flightId; }
    public String getUserId() { return userId; }
    public void setUserId(String userId) { this.userId = userId; }
    public PaymentDetails getPayment() { return payment; }
    public void setPayment(PaymentDetails payment) { this.payment = payment; }

    // Nested class for payment details
    public static class PaymentDetails {
        private String cardNo;
        private String expiry;
        // Add CVV and other fields as needed

        public String getCardNo() { return cardNo; }
        public void setCardNo(String cardNo) { this.cardNo = cardNo; }
        public String getExpiry() { return expiry; }
        public void setExpiry(String expiry) { this.expiry = expiry; }
    }
}