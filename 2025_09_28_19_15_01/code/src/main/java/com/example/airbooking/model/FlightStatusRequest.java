package com.example.airbooking.model;

import javax.validation.constraints.Pattern;

/**
 * Request model for flight status API.
 */
public class FlightStatusRequest {
    @Pattern(regexp = "^[A-Z]{2}\d{1,4}$", message = "Invalid flight number format")
    private String flightNumber;
    private String bookingRef;

    public String getFlightNumber() { return flightNumber; }
    public void setFlightNumber(String flightNumber) { this.flightNumber = flightNumber; }
    public String getBookingRef() { return bookingRef; }
    public void setBookingRef(String bookingRef) { this.bookingRef = bookingRef; }
}
