package com.example.flightsearch.model;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Map;

/**
 * Model representing a flight search request.
 */
public class FlightSearchRequest {
    @NotBlank(message = "Origin airport code is required")
    private String origin;
    @NotBlank(message = "Destination airport code is required")
    private String destination;
    @NotBlank(message = "Date is required")
    private String date; // ISO format: yyyy-MM-dd
    @NotNull(message = "Number of passengers is required")
    @Min(value = 1, message = "Number of passengers must be positive")
    private Integer passengers;
    private String sortBy;
    private Map<String, String> filter;

    public String getOrigin() { return origin; }
    public void setOrigin(String origin) { this.origin = origin; }

    public String getDestination() { return destination; }
    public void setDestination(String destination) { this.destination = destination; }

    public String getDate() { return date; }
    public void setDate(String date) { this.date = date; }

    public Integer getPassengers() { return passengers; }
    public void setPassengers(Integer passengers) { this.passengers = passengers; }

    public String getSortBy() { return sortBy; }
    public void setSortBy(String sortBy) { this.sortBy = sortBy; }

    public Map<String, String> getFilter() { return filter; }
    public void setFilter(Map<String, String> filter) { this.filter = filter; }

    @Override
    public String toString() {
        return "FlightSearchRequest{" +
                "origin='" + origin + '\'' +
                ", destination='" + destination + '\'' +
                ", date='" + date + '\'' +
                ", passengers=" + passengers +
                ", sortBy='" + sortBy + '\'' +
                ", filter=" + filter +
                '}';
    }
}
