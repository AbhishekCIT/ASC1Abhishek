package com.example.flightbooking.model;

import java.util.List;

/**
 * API response for viewing bookings.
 */
public class ViewBookingsResponse {
    private List<Booking> bookings;
    public ViewBookingsResponse(List<Booking> bookings) {
        this.bookings = bookings;
    }
    public List<Booking> getBookings() { return bookings; }
    public void setBookings(List<Booking> bookings) { this.bookings = bookings; }
}
