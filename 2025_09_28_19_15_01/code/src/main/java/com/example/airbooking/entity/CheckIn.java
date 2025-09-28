package com.example.airbooking.entity;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * Entity representing a check-in.
 */
@Entity
@Table(name = "checkin")
public class CheckIn {
    @Id
    private String checkInId;
    private String bookingId;
    private LocalDateTime checkInTime;
    private String status;

    public String getCheckInId() { return checkInId; }
    public void setCheckInId(String checkInId) { this.checkInId = checkInId; }
    public String getBookingId() { return bookingId; }
    public void setBookingId(String bookingId) { this.bookingId = bookingId; }
    public LocalDateTime getCheckInTime() { return checkInTime; }
    public void setCheckInTime(LocalDateTime checkInTime) { this.checkInTime = checkInTime; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}
