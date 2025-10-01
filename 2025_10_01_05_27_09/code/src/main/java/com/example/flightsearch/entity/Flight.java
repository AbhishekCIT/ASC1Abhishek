package com.example.flightsearch.entity;

import com.example.flightsearch.model.FlightDTO;
import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * Entity representing a flight record.
 */
@Entity
@Table(name = "flight")
public class Flight {
    @Id
    private String flightId;
    private String airline;
    private String origin;
    private String destination;
    private LocalDateTime departureTime;
    private LocalDateTime arrivalTime;
    private double price;
    private String duration;

    // Getters and setters
    public String getFlightId() { return flightId; }
    public void setFlightId(String flightId) { this.flightId = flightId; }
    public String getAirline() { return airline; }
    public void setAirline(String airline) { this.airline = airline; }
    public String getOrigin() { return origin; }
    public void setOrigin(String origin) { this.origin = origin; }
    public String getDestination() { return destination; }
    public void setDestination(String destination) { this.destination = destination; }
    public LocalDateTime getDepartureTime() { return departureTime; }
    public void setDepartureTime(LocalDateTime departureTime) { this.departureTime = departureTime; }
    public LocalDateTime getArrivalTime() { return arrivalTime; }
    public void setArrivalTime(LocalDateTime arrivalTime) { this.arrivalTime = arrivalTime; }
    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }
    public String getDuration() { return duration; }
    public void setDuration(String duration) { this.duration = duration; }

    /**
     * Returns the duration in minutes (helper for sorting).
     */
    public int getDurationMinutes() {
        // Example: parse "3h" or "2h 30m" to minutes
        if (duration == null) return 0;
        int minutes = 0;
        String[] parts = duration.split(" ");
        for (String part : parts) {
            if (part.endsWith("h")) {
                minutes += Integer.parseInt(part.replace("h", "")) * 60;
            } else if (part.endsWith("m")) {
                minutes += Integer.parseInt(part.replace("m", ""));
            }
        }
        return minutes;
    }

    /**
     * Converts this entity to a FlightDTO for response.
     */
    public FlightDTO toFlightDTO() {
        FlightDTO dto = new FlightDTO();
        dto.setFlightId(flightId);
        dto.setAirline(airline);
        dto.setOrigin(origin);
        dto.setDestination(destination);
        dto.setDepartureTime(departureTime != null ? departureTime.toString() : null);
        dto.setArrivalTime(arrivalTime != null ? arrivalTime.toString() : null);
        dto.setPrice(price);
        dto.setDuration(duration);
        return dto;
    }
}
