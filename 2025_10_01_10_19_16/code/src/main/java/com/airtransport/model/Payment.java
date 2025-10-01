package com.airtransport.model;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * Entity representing a payment.
 */
@Entity
@Table(name = "payment")
public class Payment {
    @Id
    @Column(name = "payment_id")
    private String paymentId;
    @Column(name = "booking_id")
    private String bookingId;
    private double amount;
    private String status;
    @Column(name = "payment_date")
    private LocalDateTime paymentDate;
    @Column(name = "transaction_id")
    private String transactionId;

    // Getters and setters
    public String getPaymentId() { return paymentId; }
    public void setPaymentId(String paymentId) { this.paymentId = paymentId; }
    public String getBookingId() { return bookingId; }
    public void setBookingId(String bookingId) { this.bookingId = bookingId; }
    public double getAmount() { return amount; }
    public void setAmount(double amount) { this.amount = amount; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    public LocalDateTime getPaymentDate() { return paymentDate; }
    public void setPaymentDate(LocalDateTime paymentDate) { this.paymentDate = paymentDate; }
    public String getTransactionId() { return transactionId; }
    public void setTransactionId(String transactionId) { this.transactionId = transactionId; }
}
