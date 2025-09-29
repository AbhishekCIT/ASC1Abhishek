package com.airtransport.entity;

import javax.persistence.*;
import java.time.LocalDate;

/**
 * Entity representing a booking record in the database.
 */
@Entity
@Table(name = "booking")
public class BookingEntity {
    @Id
    @Column(name = "booking_id")
    private String bookingId;

    @Column(name = "user_id", nullable = false)
    private String userId;

    @Column(name = "flight_id", nullable = false)
    private String flightId;

    @Column(name = "status", nullable = false)
    private String status;

    @Column(name = "booking_date", nullable = false)
    private LocalDate bookingDate;

    @Column(name = "confirmation_email_sent")
    private boolean confirmationEmailSent;

    // Getters and setters
    public String getBookingId() { return bookingId; }
    public void setBookingId(String bookingId) { this.bookingId = bookingId; }
    public String getUserId() { return userId; }
    public void setUserId(String userId) { this.userId = userId; }
    public String getFlightId() { return flightId; }
    public void setFlightId(String flightId) { this.flightId = flightId; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    public LocalDate getBookingDate() { return bookingDate; }
    public void setBookingDate(LocalDate bookingDate) { this.bookingDate = bookingDate; }
    public boolean isConfirmationEmailSent() { return confirmationEmailSent; }
    public void setConfirmationEmailSent(boolean confirmationEmailSent) { this.confirmationEmailSent = confirmationEmailSent; }
}
