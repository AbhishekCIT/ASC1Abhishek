package com.example.flightsearch.entity;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * Entity for search query logging.
 */
@Entity
@Table(name = "search_query")
public class SearchQuery {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_id")
    private Long userId;

    @Column(nullable = false)
    private String origin;

    @Column(nullable = false)
    private String destination;

    @Column(name = "departure_date")
    private String departureDate;

    @Column(name = "return_date")
    private String returnDate;

    @Column(columnDefinition = "TEXT")
    private String filters;

    @Column(name = "search_time")
    private LocalDateTime searchTime;

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Long getUserId() { return userId; }
    public void setUserId(Long userId) { this.userId = userId; }
    public String getOrigin() { return origin; }
    public void setOrigin(String origin) { this.origin = origin; }
    public String getDestination() { return destination; }
    public void setDestination(String destination) { this.destination = destination; }
    public String getDepartureDate() { return departureDate; }
    public void setDepartureDate(String departureDate) { this.departureDate = departureDate; }
    public String getReturnDate() { return returnDate; }
    public void setReturnDate(String returnDate) { this.returnDate = returnDate; }
    public String getFilters() { return filters; }
    public void setFilters(String filters) { this.filters = filters; }
    public LocalDateTime getSearchTime() { return searchTime; }
    public void setSearchTime(LocalDateTime searchTime) { this.searchTime = searchTime; }
}
