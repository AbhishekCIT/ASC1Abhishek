package com.airtransport.model;

/**
 * Model for Flight entity.
 */
public class Flight {
    private String flightId;
    private String airline;
    private String origin;
    private String destination;
    private String date;
    private String time;
    private double price;
    private int seatsAvailable;

    public Flight() {}

    public Flight(String flightId, String airline, String origin, String destination, String date, String time, double price, int seatsAvailable) {
        this.flightId = flightId;
        this.airline = airline;
        this.origin = origin;
        this.destination = destination;
        this.date = date;
        this.time = time;
        this.price = price;
        this.seatsAvailable = seatsAvailable;
    }

    public String getFlightId() { return flightId; }
    public void setFlightId(String flightId) { this.flightId = flightId; }
    public String getAirline() { return airline; }
    public void setAirline(String airline) { this.airline = airline; }
    public String getOrigin() { return origin; }
    public void setOrigin(String origin) { this.origin = origin; }
    public String getDestination() { return destination; }
    public void setDestination(String destination) { this.destination = destination; }
    public String getDate() { return date; }
    public void setDate(String date) { this.date = date; }
    public String getTime() { return time; }
    public void setTime(String time) { this.time = time; }
    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }
    public int getSeatsAvailable() { return seatsAvailable; }
    public void setSeatsAvailable(int seatsAvailable) { this.seatsAvailable = seatsAvailable; }
}
