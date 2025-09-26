package com.example.flightsearch.model;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

/**
 * Model for flight search request payload.
 */
public class FlightSearchRequest implements Serializable {
    @NotEmpty
    private String origin;
    @NotEmpty
    private String destination;
    @NotEmpty
    private String departureDate; // yyyy-MM-dd
    private String returnDate;    // yyyy-MM-dd (optional)
    private FlightSearchFilters filters;

    public String getOrigin() { return origin; }
    public void setOrigin(String origin) { this.origin = origin; }
    public String getDestination() { return destination; }
    public void setDestination(String destination) { this.destination = destination; }
    public String getDepartureDate() { return departureDate; }
    public void setDepartureDate(String departureDate) { this.departureDate = departureDate; }
    public String getReturnDate() { return returnDate; }
    public void setReturnDate(String returnDate) { this.returnDate = returnDate; }
    public FlightSearchFilters getFilters() { return filters; }
    public void setFilters(FlightSearchFilters filters) { this.filters = filters; }
}
