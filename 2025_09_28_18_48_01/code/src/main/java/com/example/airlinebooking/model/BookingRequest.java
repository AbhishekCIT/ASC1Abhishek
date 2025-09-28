package com.example.airlinebooking.model;

import jakarta.validation.constraints.NotNull;
import java.util.List;

/**
 * Request model for booking a flight.
 */
public class BookingRequest {
    @NotNull(message = "Flight ID is required")
    private Integer flightId;

    @NotNull(message = "Passenger details are required")
    private List<PassengerDetails> passengerDetails;

    @NotNull(message = "Payment details are required")
    private PaymentDetails paymentDetails;

    public Integer getFlightId() { return flightId; }
    public void setFlightId(Integer flightId) { this.flightId = flightId; }
    public List<PassengerDetails> getPassengerDetails() { return passengerDetails; }
    public void setPassengerDetails(List<PassengerDetails> passengerDetails) { this.passengerDetails = passengerDetails; }
    public PaymentDetails getPaymentDetails() { return paymentDetails; }
    public void setPaymentDetails(PaymentDetails paymentDetails) { this.paymentDetails = paymentDetails; }

    /**
     * Model for passenger details in booking request.
     */
    public static class PassengerDetails {
        private String name;
        private String email;
        private String phone;
        public String getName() { return name; }
        public void setName(String name) { this.name = name; }
        public String getEmail() { return email; }
        public void setEmail(String email) { this.email = email; }
        public String getPhone() { return phone; }
        public void setPhone(String phone) { this.phone = phone; }
    }

    /**
     * Model for payment details in booking request.
     */
    public static class PaymentDetails {
        private String cardNumber;
        private String cardHolder;
        private String expiryDate;
        private String cvv;
        private String paymentMethod; // e.g., STRIPE, PAYPAL
        public String getCardNumber() { return cardNumber; }
        public void setCardNumber(String cardNumber) { this.cardNumber = cardNumber; }
        public String getCardHolder() { return cardHolder; }
        public void setCardHolder(String cardHolder) { this.cardHolder = cardHolder; }
        public String getExpiryDate() { return expiryDate; }
        public void setExpiryDate(String expiryDate) { this.expiryDate = expiryDate; }
        public String getCvv() { return cvv; }
        public void setCvv(String cvv) { this.cvv = cvv; }
        public String getPaymentMethod() { return paymentMethod; }
        public void setPaymentMethod(String paymentMethod) { this.paymentMethod = paymentMethod; }
    }
}
