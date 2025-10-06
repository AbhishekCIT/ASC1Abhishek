package com.example.airbooking.model;

import javax.persistence.*;

@Entity
@Table(name = "baggage")
public class Baggage {
    @Id
    private String baggageId;
    private String bookingId;
    private double weight;
    private String status;
    private String location;

    // Getters and Setters
    public String getBaggageId() { return baggageId; }
    public void setBaggageId(String baggageId) { this.baggageId = baggageId; }
    public String getBookingId() { return bookingId; }
    public void setBookingId(String bookingId) { this.bookingId = bookingId; }
    public double getWeight() { return weight; }
    public void setWeight(double weight) { this.weight = weight; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    public String getLocation() { return location; }
    public void setLocation(String location) { this.location = location; }
}