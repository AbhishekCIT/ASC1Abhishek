package com.airtransport.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import javax.validation.constraints.*;
import java.time.LocalDate;
import java.util.Map;

/**
 * Model for flight search request payload.
 */
public class FlightSearchRequest {
    @NotBlank
    private String source;
    @NotBlank
    private String destination;
    @NotNull
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate date;
    @Min(1)
    private int passengerCount;
    private Map<String, Object> filters;
    private String userId; // For logging/audit

    public FlightSearchRequest() {}

    public String getSource() { return source; }
    public void setSource(String source) { this.source = source; }
    public String getDestination() { return destination; }
    public void setDestination(String destination) { this.destination = destination; }
    public LocalDate getDate() { return date; }
    public void setDate(LocalDate date) { this.date = date; }
    public int getPassengerCount() { return passengerCount; }
    public void setPassengerCount(int passengerCount) { this.passengerCount = passengerCount; }
    public Map<String, Object> getFilters() { return filters; }
    public void setFilters(Map<String, Object> filters) { this.filters = filters; }
    public String getUserId() { return userId; }
    public void setUserId(String userId) { this.userId = userId; }
}
