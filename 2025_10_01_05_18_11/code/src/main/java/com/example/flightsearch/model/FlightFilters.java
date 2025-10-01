package com.example.flightsearch.model;

/**
 * Model representing filters for flight search.
 */
public class FlightFilters {
    private String airline;
    private Double maxPrice;
    private Integer stops;

    // Getters and Setters
    public String getAirline() { return airline; }
    public void setAirline(String airline) { this.airline = airline; }
    public Double getMaxPrice() { return maxPrice; }
    public void setMaxPrice(Double maxPrice) { this.maxPrice = maxPrice; }
    public Integer getStops() { return stops; }
    public void setStops(Integer stops) { this.stops = stops; }

    @Override
    public String toString() {
        return "{\"airline\":\"" + airline + "\",\"maxPrice\":" + maxPrice + ",\"stops\":" + stops + "}";
    }
}
