package com.example.airtransport.model;

/**
 * Model representing a flight entity.
 */
public class Flight {
    private String flightId;
    private String airline;
    private String destination;
    private String departureDate;
    private double price;
    private String duration;
    private int seatsAvailable;

    public Flight() {}

    public Flight(String flightId, String airline, String destination, String departureDate, double price, String duration, int seatsAvailable) {
        this.flightId = flightId;
        this.airline = airline;
        this.destination = destination;
        this.departureDate = departureDate;
        this.price = price;
        this.duration = duration;
        this.seatsAvailable = seatsAvailable;
    }

    public String getFlightId() { return flightId; }
    public void setFlightId(String flightId) { this.flightId = flightId; }
    public String getAirline() { return airline; }
    public void setAirline(String airline) { this.airline = airline; }
    public String getDestination() { return destination; }
    public void setDestination(String destination) { this.destination = destination; }
    public String getDepartureDate() { return departureDate; }
    public void setDepartureDate(String departureDate) { this.departureDate = departureDate; }
    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }
    public String getDuration() { return duration; }
    public void setDuration(String duration) { this.duration = duration; }
    public int getSeatsAvailable() { return seatsAvailable; }
    public void setSeatsAvailable(int seatsAvailable) { this.seatsAvailable = seatsAvailable; }
}
