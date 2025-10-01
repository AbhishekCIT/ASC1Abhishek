package com.example.flightsearch.model;

import java.util.List;

/**
 * Model for flight search response.
 */
public class FlightSearchResponse {
    private List<FlightDTO> flights;
    private int totalPages;
    private int currentPage;
    private String error;

    public FlightSearchResponse() {}
    public FlightSearchResponse(String error) {
        this.error = error;
    }

    public List<FlightDTO> getFlights() { return flights; }
    public void setFlights(List<FlightDTO> flights) { this.flights = flights; }
    public int getTotalPages() { return totalPages; }
    public void setTotalPages(int totalPages) { this.totalPages = totalPages; }
    public int getCurrentPage() { return currentPage; }
    public void setCurrentPage(int currentPage) { this.currentPage = currentPage; }
    public String getError() { return error; }
    public void setError(String error) { this.error = error; }
}
