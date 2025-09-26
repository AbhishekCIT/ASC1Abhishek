package com.example.flightsearch.model;

import java.io.Serializable;
import java.util.List;

/**
 * Model for flight search filters (airlines, price range, stops).
 */
public class FlightSearchFilters implements Serializable {
    private List<String> airlines;
    private Integer[] priceRange; // [min, max]
    private Integer stops;

    public List<String> getAirlines() { return airlines; }
    public void setAirlines(List<String> airlines) { this.airlines = airlines; }
    public Integer[] getPriceRange() { return priceRange; }
    public void setPriceRange(Integer[] priceRange) { this.priceRange = priceRange; }
    public Integer getStops() { return stops; }
    public void setStops(Integer stops) { this.stops = stops; }
}
