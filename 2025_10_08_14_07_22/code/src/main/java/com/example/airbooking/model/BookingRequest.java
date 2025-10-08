package com.example.airbooking.model;

import java.util.Map;

/**
 * Request model for booking a flight.
 */
public class BookingRequest {
    private String flightId;
    private String userId;
    private Map<String, Object> passengerDetails;
    private PaymentInfo paymentInfo;

    public String getFlightId() { return flightId; }
    public void setFlightId(String flightId) { this.flightId = flightId; }
    public String getUserId() { return userId; }
    public void setUserId(String userId) { this.userId = userId; }
    public Map<String, Object> getPassengerDetails() { return passengerDetails; }
    public void setPassengerDetails(Map<String, Object> passengerDetails) { this.passengerDetails = passengerDetails; }
    public PaymentInfo getPaymentInfo() { return paymentInfo; }
    public void setPaymentInfo(PaymentInfo paymentInfo) { this.paymentInfo = paymentInfo; }

    /**
     * Inner class for payment info.
     */
    public static class PaymentInfo {
        private double amount;
        private String cardNumber;
        private String expiry;
        private String cvv;
        // Add more fields as needed
        public double getAmount() { return amount; }
        public void setAmount(double amount) { this.amount = amount; }
        public String getCardNumber() { return cardNumber; }
        public void setCardNumber(String cardNumber) { this.cardNumber = cardNumber; }
        public String getExpiry() { return expiry; }
        public void setExpiry(String expiry) { this.expiry = expiry; }
        public String getCvv() { return cvv; }
        public void setCvv(String cvv) { this.cvv = cvv; }
    }
}
