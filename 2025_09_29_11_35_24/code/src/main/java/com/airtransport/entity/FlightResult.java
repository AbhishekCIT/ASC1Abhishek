package com.airtransport.entity;

import javax.persistence.*;

/**
 * Entity representing a flight result log.
 */
@Entity
@Table(name = "flight_result")
public class FlightResult {
    @Id
    private String id;
    private String searchQueryId;
    private String airline;
    private String flightNumber;
    private String departureTime;
    private String arrivalTime;
    private String duration;
    private double price;

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public String getSearchQueryId() { return searchQueryId; }
    public void setSearchQueryId(String searchQueryId) { this.searchQueryId = searchQueryId; }
    public String getAirline() { return airline; }
    public void setAirline(String airline) { this.airline = airline; }
    public String getFlightNumber() { return flightNumber; }
    public void setFlightNumber(String flightNumber) { this.flightNumber = flightNumber; }
    public String getDepartureTime() { return departureTime; }
    public void setDepartureTime(String departureTime) { this.departureTime = departureTime; }
    public String getArrivalTime() { return arrivalTime; }
    public void setArrivalTime(String arrivalTime) { this.arrivalTime = arrivalTime; }
    public String getDuration() { return duration; }
    public void setDuration(String duration) { this.duration = duration; }
    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }
}
