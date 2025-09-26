package com.example.flightbooking.model;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * Entity representing a payment.
 */
@Entity
public class Payment {
    @Id
    private String paymentId;
    private String bookingId;
    private double amount;
    private String status;
    private LocalDateTime paymentTime;

    // Getters and Setters
    public String getPaymentId() { return paymentId; }
    public void setPaymentId(String paymentId) { this.paymentId = paymentId; }
    public String getBookingId() { return bookingId; }
    public void setBookingId(String bookingId) { this.bookingId = bookingId; }
    public double getAmount() { return amount; }
    public void setAmount(double amount) { this.amount = amount; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    public LocalDateTime getPaymentTime() { return paymentTime; }
    public void setPaymentTime(LocalDateTime paymentTime) { this.paymentTime = paymentTime; }
}
