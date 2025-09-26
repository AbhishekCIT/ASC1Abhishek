package com.example.flightbooking.model;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Entity/model for booking details.
 */
@Entity
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private User user;

    @ManyToOne
    private Flight flight;

    private String bookingReference;
    private LocalDateTime bookingDate;
    private String status;

    @OneToMany(cascade = CascadeType.ALL)
    private List<PassengerDetail> passengerDetails;

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }
    public Flight getFlight() { return flight; }
    public void setFlight(Flight flight) { this.flight = flight; }
    public String getBookingReference() { return bookingReference; }
    public void setBookingReference(String bookingReference) { this.bookingReference = bookingReference; }
    public LocalDateTime getBookingDate() { return bookingDate; }
    public void setBookingDate(LocalDateTime bookingDate) { this.bookingDate = bookingDate; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    public List<PassengerDetail> getPassengerDetails() { return passengerDetails; }
    public void setPassengerDetails(List<PassengerDetail> passengerDetails) { this.passengerDetails = passengerDetails; }
}
