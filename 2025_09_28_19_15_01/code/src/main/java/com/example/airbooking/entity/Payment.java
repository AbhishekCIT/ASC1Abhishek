package com.example.airbooking.entity;

import javax.persistence.*;

/**
 * Entity representing a payment for a booking.
 */
@Entity
@Table(name = "payment")
public class Payment {
    @Id
    private String paymentId;
    private String bookingId;
    private double amount;
    private String method;
    private String status;

    public String getPaymentId() { return paymentId; }
    public void setPaymentId(String paymentId) { this.paymentId = paymentId; }
    public String getBookingId() { return bookingId; }
    public void setBookingId(String bookingId) { this.bookingId = bookingId; }
    public double getAmount() { return amount; }
    public void setAmount(double amount) { this.amount = amount; }
    public String getMethod() { return method; }
    public void setMethod(String method) { this.method = method; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}
