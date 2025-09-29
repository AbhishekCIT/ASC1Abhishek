package com.airbook.model;

import java.time.LocalDateTime;

/**
 * Model for Refund entity
 */
public class Refund {
    private String refundId;
    private String bookingId;
    private double amount;
    private String status;
    private LocalDateTime processedAt;

    // Getters and setters
    public String getRefundId() { return refundId; }
    public void setRefundId(String refundId) { this.refundId = refundId; }
    public String getBookingId() { return bookingId; }
    public void setBookingId(String bookingId) { this.bookingId = bookingId; }
    public double getAmount() { return amount; }
    public void setAmount(double amount) { this.amount = amount; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    public LocalDateTime getProcessedAt() { return processedAt; }
    public void setProcessedAt(LocalDateTime processedAt) { this.processedAt = processedAt; }
}
