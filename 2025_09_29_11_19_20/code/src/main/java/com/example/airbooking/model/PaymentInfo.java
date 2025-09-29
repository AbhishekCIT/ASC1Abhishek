package com.example.airbooking.model;

/**
 * Model for payment information provided by the user.
 */
public class PaymentInfo {
    private String cardNumber;
    private String cardHolder;
    private String expiry;
    private String cvv;
    private String paymentMethod;

    // Getters and Setters
    public String getCardNumber() { return cardNumber; }
    public void setCardNumber(String cardNumber) { this.cardNumber = cardNumber; }
    public String getCardHolder() { return cardHolder; }
    public void setCardHolder(String cardHolder) { this.cardHolder = cardHolder; }
    public String getExpiry() { return expiry; }
    public void setExpiry(String expiry) { this.expiry = expiry; }
    public String getCvv() { return cvv; }
    public void setCvv(String cvv) { this.cvv = cvv; }
    public String getPaymentMethod() { return paymentMethod; }
    public void setPaymentMethod(String paymentMethod) { this.paymentMethod = paymentMethod; }

    /**
     * Validates the payment info (dummy validation for demo).
     * @return true if valid, false otherwise
     */
    public boolean isValid() {
        return cardNumber != null && cardHolder != null && expiry != null && cvv != null && paymentMethod != null;
    }
}
