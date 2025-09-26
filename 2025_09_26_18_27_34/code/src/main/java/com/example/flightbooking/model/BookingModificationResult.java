package com.example.flightbooking.model;

/**
 * Model for booking modification response/result.
 */
public class BookingModificationResult {
    private String bookingReference;
    private String status;
    private double fees;
    private double refund;

    public BookingModificationResult(String bookingReference, String status, double fees, double refund) {
        this.bookingReference = bookingReference;
        this.status = status;
        this.fees = fees;
        this.refund = refund;
    }

    // Getters and Setters
    public String getBookingReference() { return bookingReference; }
    public void setBookingReference(String bookingReference) { this.bookingReference = bookingReference; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    public double getFees() { return fees; }
    public void setFees(double fees) { this.fees = fees; }
    public double getRefund() { return refund; }
    public void setRefund(double refund) { this.refund = refund; }
}
