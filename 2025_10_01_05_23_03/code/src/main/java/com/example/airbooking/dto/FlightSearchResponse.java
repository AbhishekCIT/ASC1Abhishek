package com.example.airbooking.dto;

import java.math.BigDecimal;

/**
 * DTO for flight search response.
 */
public class FlightSearchResponse {
    private Integer flightId;
    private String airline;
    private BigDecimal price;
    private int seatsAvailable;
    private String seatClass;

    // Getters and Setters
    public Integer getFlightId() { return flightId; }
    public void setFlightId(Integer flightId) { this.flightId = flightId; }
    public String getAirline() { return airline; }
    public void setAirline(String airline) { this.airline = airline; }
    public BigDecimal getPrice() { return price; }
    public void setPrice(BigDecimal price) { this.price = price; }
    public int getSeatsAvailable() { return seatsAvailable; }
    public void setSeatsAvailable(int seatsAvailable) { this.seatsAvailable = seatsAvailable; }
    public String getSeatClass() { return seatClass; }
    public void setSeatClass(String seatClass) { this.seatClass = seatClass; }
}
