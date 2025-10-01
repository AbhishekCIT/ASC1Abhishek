package com.airtransport.model;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Map;

/**
 * Entity representing a booking.
 */
@Entity
@Table(name = "booking")
public class Booking {
    @Id
    @Column(name = "booking_id")
    private String bookingId;
    @Column(name = "user_id")
    private String userId;
    @Column(name = "flight_id")
    private String flightId;
    private String status;
    @Column(name = "ticket_number", unique = true)
    private String ticketNumber;
    @Column(name = "booking_date")
    private LocalDateTime bookingDate;
    @Transient
    private Map<String, Object> passengerInfo;
    @Transient
    private double price;

    // Getters and setters
    public String getBookingId() { return bookingId; }
    public void setBookingId(String bookingId) { this.bookingId = bookingId; }
    public String getUserId() { return userId; }
    public void setUserId(String userId) { this.userId = userId; }
    public String getFlightId() { return flightId; }
    public void setFlightId(String flightId) { this.flightId = flightId; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    public String getTicketNumber() { return ticketNumber; }
    public void setTicketNumber(String ticketNumber) { this.ticketNumber = ticketNumber; }
    public LocalDateTime getBookingDate() { return bookingDate; }
    public void setBookingDate(LocalDateTime bookingDate) { this.bookingDate = bookingDate; }
    public Map<String, Object> getPassengerInfo() { return passengerInfo; }
    public void setPassengerInfo(Map<String, Object> passengerInfo) { this.passengerInfo = passengerInfo; }
    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }
}
