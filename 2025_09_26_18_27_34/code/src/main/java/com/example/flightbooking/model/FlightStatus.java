package com.example.flightbooking.model;

import java.util.List;

/**
 * Model for flight status response.
 */
public class FlightStatus {
    private String flightNumber;
    private String status;
    private String departureTime;
    private String arrivalTime;
    private String gate;
    private List<FlightStatusUpdate> updates;

    // Getters and Setters
    public String getFlightNumber() { return flightNumber; }
    public void setFlightNumber(String flightNumber) { this.flightNumber = flightNumber; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    public String getDepartureTime() { return departureTime; }
    public void setDepartureTime(String departureTime) { this.departureTime = departureTime; }
    public String getArrivalTime() { return arrivalTime; }
    public void setArrivalTime(String arrivalTime) { this.arrivalTime = arrivalTime; }
    public String getGate() { return gate; }
    public void setGate(String gate) { this.gate = gate; }
    public List<FlightStatusUpdate> getUpdates() { return updates; }
    public void setUpdates(List<FlightStatusUpdate> updates) { this.updates = updates; }
}
