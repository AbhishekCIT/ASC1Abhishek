package com.example.airbooking.entity;

import javax.persistence.*;

/**
 * Entity representing a passenger in a booking.
 */
@Entity
@Table(name = "passenger")
public class Passenger {
    @Id
    private String passengerId;
    private String bookingId;
    private String name;
    private String email;
    private String seat;

    public String getPassengerId() { return passengerId; }
    public void setPassengerId(String passengerId) { this.passengerId = passengerId; }
    public String getBookingId() { return bookingId; }
    public void setBookingId(String bookingId) { this.bookingId = bookingId; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getSeat() { return seat; }
    public void setSeat(String seat) { this.seat = seat; }
}
