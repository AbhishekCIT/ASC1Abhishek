package com.example.airtransport.model;

import java.time.LocalDate;
import java.util.List;

/**
 * Model representing search criteria for flight search API.
 */
public class FlightSearchCriteria {
    private String origin;
    private String destination;
    private LocalDate departureDate;
    private LocalDate returnDate;
    private List<String> airlines;
    private PriceRange priceRange;
    private Integer stops;
    private int page = 1;
    private int size = 20;

    // Getters and setters
    public String getOrigin() { return origin; }
    public void setOrigin(String origin) { this.origin = origin; }
    public String getDestination() { return destination; }
    public void setDestination(String destination) { this.destination = destination; }
    public LocalDate getDepartureDate() { return departureDate; }
    public void setDepartureDate(LocalDate departureDate) { this.departureDate = departureDate; }
    public LocalDate getReturnDate() { return returnDate; }
    public void setReturnDate(LocalDate returnDate) { this.returnDate = returnDate; }
    public List<String> getAirlines() { return airlines; }
    public void setAirlines(List<String> airlines) { this.airlines = airlines; }
    public PriceRange getPriceRange() { return priceRange; }
    public void setPriceRange(PriceRange priceRange) { this.priceRange = priceRange; }
    public Integer getStops() { return stops; }
    public void setStops(Integer stops) { this.stops = stops; }
    public int getPage() { return page; }
    public void setPage(int page) { this.page = page; }
    public int getSize() { return size; }
    public void setSize(int size) { this.size = size; }
}
