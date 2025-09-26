package com.example.flightbooking.model;

/**
 * Model for booking cancellation response/result.
 */
public class BookingCancellationResult {
    private String bookingReference;
    private String status;
    private double refund;
    private double fees;

    public BookingCancellationResult(String bookingReference, String status, double refund, double fees) {
        this.bookingReference = bookingReference;
        this.status = status;
        this.refund = refund;
        this.fees = fees;
    }

    // Getters and Setters
    public String getBookingReference() { return bookingReference; }
    public void setBookingReference(String bookingReference) { this.bookingReference = bookingReference; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    public double getRefund() { return refund; }
    public void setRefund(double refund) { this.refund = refund; }
    public double getFees() { return fees; }
    public void setFees(double fees) { this.fees = fees; }
}
