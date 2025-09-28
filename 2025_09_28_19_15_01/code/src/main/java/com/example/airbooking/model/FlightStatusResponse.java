package com.example.airbooking.model;

/**
 * Response model for flight status API.
 */
public class FlightStatusResponse {
    private String flightNumber;
    private String status;
    private String gate;
    private String departure;
    private String arrival;

    public String getFlightNumber() { return flightNumber; }
    public void setFlightNumber(String flightNumber) { this.flightNumber = flightNumber; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    public String getGate() { return gate; }
    public void setGate(String gate) { this.gate = gate; }
    public String getDeparture() { return departure; }
    public void setDeparture(String departure) { this.departure = departure; }
    public String getArrival() { return arrival; }
    public void setArrival(String arrival) { this.arrival = arrival; }
}
