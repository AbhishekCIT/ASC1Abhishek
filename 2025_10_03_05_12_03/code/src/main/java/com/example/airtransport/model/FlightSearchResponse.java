package com.example.airtransport.model;

import java.util.List;

/**
 * Model representing paginated flight search response.
 */
public class FlightSearchResponse {
    private List<Flight> flights;
    private int total;
    private int page;
    private int size;

    public FlightSearchResponse(List<Flight> flights, int total, int page, int size) {
        this.flights = flights;
        this.total = total;
        this.page = page;
        this.size = size;
    }

    public List<Flight> getFlights() { return flights; }
    public void setFlights(List<Flight> flights) { this.flights = flights; }
    public int getTotal() { return total; }
    public void setTotal(int total) { this.total = total; }
    public int getPage() { return page; }
    public void setPage(int page) { this.page = page; }
    public int getSize() { return size; }
    public void setSize(int size) { this.size = size; }
}
