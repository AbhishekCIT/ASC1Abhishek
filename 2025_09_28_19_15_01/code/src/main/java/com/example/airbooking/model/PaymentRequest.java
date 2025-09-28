package com.example.airbooking.model;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Map;

/**
 * Request model for processing a payment.
 */
public class PaymentRequest {
    @NotBlank(message = "Booking ID is required")
    private String bookingId;
    @NotNull(message = "Amount is required")
    private double amount;
    @NotBlank(message = "Payment method is required")
    private String method;
    @NotNull(message = "Payment details are required")
    private Map<String, Object> details;

    public String getBookingId() { return bookingId; }
    public void setBookingId(String bookingId) { this.bookingId = bookingId; }
    public double getAmount() { return amount; }
    public void setAmount(double amount) { this.amount = amount; }
    public String getMethod() { return method; }
    public void setMethod(String method) { this.method = method; }
    public Map<String, Object> getDetails() { return details; }
    public void setDetails(Map<String, Object> details) { this.details = details; }
}
