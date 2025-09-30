package com.airtransport.model;

/**
 * Request model for booking a flight.
 */
public class BookFlightRequest {
    private String flightId;
    private PassengerDTO passenger;
    private PaymentDetails payment;

    // Getters and Setters
    public String getFlightId() { return flightId; }
    public void setFlightId(String flightId) { this.flightId = flightId; }
    public PassengerDTO getPassenger() { return passenger; }
    public void setPassenger(PassengerDTO passenger) { this.passenger = passenger; }
    public PaymentDetails getPayment() { return payment; }
    public void setPayment(PaymentDetails payment) { this.payment = payment; }

    /**
     * Nested class for payment details.
     */
    public static class PaymentDetails {
        private String cardNumber;
        private String expiry;
        private String cvv;

        // Getters and Setters
        public String getCardNumber() { return cardNumber; }
        public void setCardNumber(String cardNumber) { this.cardNumber = cardNumber; }
        public String getExpiry() { return expiry; }
        public void setExpiry(String expiry) { this.expiry = expiry; }
        public String getCvv() { return cvv; }
        public void setCvv(String cvv) { this.cvv = cvv; }
    }
}
