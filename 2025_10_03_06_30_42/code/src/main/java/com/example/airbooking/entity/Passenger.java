package com.example.airbooking.entity;

import javax.persistence.*;

/**
 * Entity representing a Passenger.
 */
@Entity
@Table(name = "passenger")
public class Passenger {
    @Id
    @Column(name = "passenger_id", nullable = false, unique = true)
    private String passengerId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "booking_id", nullable = false)
    private Booking booking;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "passport_number", nullable = false)
    private String passportNumber;

    @Column(name = "nationality", nullable = false)
    private String nationality;

    // Getters and Setters
    public String getPassengerId() { return passengerId; }
    public void setPassengerId(String passengerId) { this.passengerId = passengerId; }
    public Booking getBooking() { return booking; }
    public void setBooking(Booking booking) { this.booking = booking; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getPassportNumber() { return passportNumber; }
    public void setPassportNumber(String passportNumber) { this.passportNumber = passportNumber; }
    public String getNationality() { return nationality; }
    public void setNationality(String nationality) { this.nationality = nationality; }
}
