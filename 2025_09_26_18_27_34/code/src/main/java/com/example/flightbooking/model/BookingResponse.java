package com.example.flightbooking.model;

/**
 * API response for booking confirmation.
 */
public class BookingResponse {
    private Booking booking;

    public BookingResponse(Booking booking) {
        this.booking = booking;
    }

    public Booking getBooking() { return booking; }
    public void setBooking(Booking booking) { this.booking = booking; }
}
