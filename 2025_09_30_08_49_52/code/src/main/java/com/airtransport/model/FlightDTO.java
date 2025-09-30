package com.airtransport.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * Data Transfer Object for Flight.
 */
public class FlightDTO {
    private String flightId;
    private String airline;
    private LocalDateTime departure;
    private LocalDateTime arrival;
    private BigDecimal price;
    private String flightClass;

    // Getters and Setters
    public String getFlightId() { return flightId; }
    public void setFlightId(String flightId) { this.flightId = flightId; }
    public String getAirline() { return airline; }
    public void setAirline(String airline) { this.airline = airline; }
    public LocalDateTime getDeparture() { return departure; }
    public void setDeparture(LocalDateTime departure) { this.departure = departure; }
    public LocalDateTime getArrival() { return arrival; }
    public void setArrival(LocalDateTime arrival) { this.arrival = arrival; }
    public BigDecimal getPrice() { return price; }
    public void setPrice(BigDecimal price) { this.price = price; }
    public String getFlightClass() { return flightClass; }
    public void setFlightClass(String flightClass) { this.flightClass = flightClass; }
}
