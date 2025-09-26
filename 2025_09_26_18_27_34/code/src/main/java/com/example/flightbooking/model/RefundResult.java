package com.example.flightbooking.model;

import java.time.LocalDateTime;

/**
 * Model for refund processing result.
 */
public class RefundResult {
    private double amount;
    private LocalDateTime processedAt;
    private String status;

    // Getters and Setters
    public double getAmount() { return amount; }
    public void setAmount(double amount) { this.amount = amount; }
    public LocalDateTime getProcessedAt() { return processedAt; }
    public void setProcessedAt(LocalDateTime processedAt) { this.processedAt = processedAt; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}
