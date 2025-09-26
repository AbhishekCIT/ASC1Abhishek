package com.example.airbooking.model;

/**
 * Model for payment request payload.
 */
public class PaymentRequest {
    private Long bookingId;
    private String cardNumber;
    private String cardExpiry;
    private String cardCvv;
    private String cardHolderName;
    private Double amount;

    public Long getBookingId() { return bookingId; }
    public void setBookingId(Long bookingId) { this.bookingId = bookingId; }
    public String getCardNumber() { return cardNumber; }
    public void setCardNumber(String cardNumber) { this.cardNumber = cardNumber; }
    public String getCardExpiry() { return cardExpiry; }
    public void setCardExpiry(String cardExpiry) { this.cardExpiry = cardExpiry; }
    public String getCardCvv() { return cardCvv; }
    public void setCardCvv(String cardCvv) { this.cardCvv = cardCvv; }
    public String getCardHolderName() { return cardHolderName; }
    public void setCardHolderName(String cardHolderName) { this.cardHolderName = cardHolderName; }
    public Double getAmount() { return amount; }
    public void setAmount(Double amount) { this.amount = amount; }
    public boolean isValid() {
        return cardNumber != null && cardNumber.length() >= 12 && cardExpiry != null && cardCvv != null && cardHolderName != null && amount != null && amount > 0;
    }
}
