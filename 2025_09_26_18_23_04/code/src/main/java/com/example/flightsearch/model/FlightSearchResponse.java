package com.example.flightsearch.model;

import java.time.LocalDateTime;

/**
 * Model representing the flight search response.
 */
public class FlightSearchResponse {
    private long flightId;
    private String airline;
    private LocalDateTime departure;
    private LocalDateTime arrival;
    private String duration;
    private int layovers;
    private double price;

    // Getters and Setters
    public long getFlightId() { return flightId; }
    public void setFlightId(long flightId) { this.flightId = flightId; }
    public String getAirline() { return airline; }
    public void setAirline(String airline) { this.airline = airline; }
    public LocalDateTime getDeparture() { return departure; }
    public void setDeparture(LocalDateTime departure) { this.departure = departure; }
    public LocalDateTime getArrival() { return arrival; }
    public void setArrival(LocalDateTime arrival) { this.arrival = arrival; }
    public String getDuration() { return duration; }
    public void setDuration(String duration) { this.duration = duration; }
    public int getLayovers() { return layovers; }
    public void setLayovers(int layovers) { this.layovers = layovers; }
    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }

    /**
     * Map Flight entity to FlightSearchResponse.
     */
    public static FlightSearchResponse fromEntity(Flight flight) {
        FlightSearchResponse resp = new FlightSearchResponse();
        resp.setFlightId(flight.getFlightId());
        resp.setAirline(flight.getAirline());
        resp.setDeparture(flight.getDepartureTime());
        resp.setArrival(flight.getArrivalTime());
        resp.setDuration(flight.getDuration());
        resp.setLayovers(flight.getLayovers());
        resp.setPrice(flight.getPrice());
        return resp;
    }
}
