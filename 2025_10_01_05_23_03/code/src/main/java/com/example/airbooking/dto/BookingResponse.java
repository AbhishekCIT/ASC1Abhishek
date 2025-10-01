package com.example.airbooking.dto;

import java.math.BigDecimal;
import java.util.Map;

/**
 * DTO for booking response.
 */
public class BookingResponse {
    private Integer bookingId;
    private String status;
    private BigDecimal totalFare;
    private Map<String, Object> flightDetails;
    private String error;

    // Getters and Setters
    public Integer getBookingId() { return bookingId; }
    public void setBookingId(Integer bookingId) { this.bookingId = bookingId; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    public BigDecimal getTotalFare() { return totalFare; }
    public void setTotalFare(BigDecimal totalFare) { this.totalFare = totalFare; }
    public Map<String, Object> getFlightDetails() { return flightDetails; }
    public void setFlightDetails(Map<String, Object> flightDetails) { this.flightDetails = flightDetails; }
    public String getError() { return error; }
    public void setError(String error) { this.error = error; }
}
