package com.example.flightsearch.entity;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * Entity representing search criteria for flights.
 */
@Entity
@Table(name = "SEARCH_CRITERIA")
public class SearchCriteria {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String userId;
    private String origin;
    private String destination;
    private LocalDate departureDate;
    private LocalDate returnDate;
    @Column(columnDefinition = "TEXT")
    private String filters; // JSON string
    private LocalDateTime searchTimestamp;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getUserId() { return userId; }
    public void setUserId(String userId) { this.userId = userId; }
    public String getOrigin() { return origin; }
    public void setOrigin(String origin) { this.origin = origin; }
    public String getDestination() { return destination; }
    public void setDestination(String destination) { this.destination = destination; }
    public LocalDate getDepartureDate() { return departureDate; }
    public void setDepartureDate(LocalDate departureDate) { this.departureDate = departureDate; }
    public LocalDate getReturnDate() { return returnDate; }
    public void setReturnDate(LocalDate returnDate) { this.returnDate = returnDate; }
    public String getFilters() { return filters; }
    public void setFilters(String filters) { this.filters = filters; }
    public LocalDateTime getSearchTimestamp() { return searchTimestamp; }
    public void setSearchTimestamp(LocalDateTime searchTimestamp) { this.searchTimestamp = searchTimestamp; }
}
