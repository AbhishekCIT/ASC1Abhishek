package com.example.booking.model;

import java.util.List;

/**
 * Model representing a booking record.
 */
public class Booking {
    private String bookingReference;
    private String status;
    private String flightId;
    private List<PassengerDetails> passengerDetails;
    private List<String> seatSelection;
    private String eTicket;

    public Booking() {}

    public Booking(String bookingReference, String status, String flightId, List<PassengerDetails> passengerDetails, List<String> seatSelection, String eTicket) {
        this.bookingReference = bookingReference;
        this.status = status;
        this.flightId = flightId;
        this.passengerDetails = passengerDetails;
        this.seatSelection = seatSelection;
        this.eTicket = eTicket;
    }

    public String getBookingReference() { return bookingReference; }
    public void setBookingReference(String bookingReference) { this.bookingReference = bookingReference; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public String getFlightId() { return flightId; }
    public void setFlightId(String flightId) { this.flightId = flightId; }

    public List<PassengerDetails> getPassengerDetails() { return passengerDetails; }
    public void setPassengerDetails(List<PassengerDetails> passengerDetails) { this.passengerDetails = passengerDetails; }

    public List<String> getSeatSelection() { return seatSelection; }
    public void setSeatSelection(List<String> seatSelection) { this.seatSelection = seatSelection; }

    public String getETicket() { return eTicket; }
    public void setETicket(String eTicket) { this.eTicket = eTicket; }
}
