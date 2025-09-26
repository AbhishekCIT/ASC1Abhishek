package com.example.flightbooking.model;

import java.time.LocalDate;
import java.util.List;

/**
 * Model for booking modification request.
 */
public class BookingModificationRequest {
    private LocalDate newTravelDate;
    private List<PassengerDetail> passengerDetails;

    // Getters and Setters
    public LocalDate getNewTravelDate() { return newTravelDate; }
    public void setNewTravelDate(LocalDate newTravelDate) { this.newTravelDate = newTravelDate; }
    public List<PassengerDetail> getPassengerDetails() { return passengerDetails; }
    public void setPassengerDetails(List<PassengerDetail> passengerDetails) { this.passengerDetails = passengerDetails; }
}
