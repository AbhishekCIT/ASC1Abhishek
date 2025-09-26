package com.airtransport.entity;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * Entity representing the SEARCH_QUERY table.
 */
@Entity
@Table(name = "search_query")
public class SearchQueryEntity {
    @Id
    @Column(name = "query_id")
    private String queryId;

    @Column(name = "user_id")
    private String userId;

    @Column(name = "origin")
    private String origin;

    @Column(name = "destination")
    private String destination;

    @Column(name = "search_time")
    private LocalDateTime searchTime;

    @Column(name = "filters")
    private String filters;

    // Getters and setters
    public String getQueryId() { return queryId; }
    public void setQueryId(String queryId) { this.queryId = queryId; }
    public String getUserId() { return userId; }
    public void setUserId(String userId) { this.userId = userId; }
    public String getOrigin() { return origin; }
    public void setOrigin(String origin) { this.origin = origin; }
    public String getDestination() { return destination; }
    public void setDestination(String destination) { this.destination = destination; }
    public LocalDateTime getSearchTime() { return searchTime; }
    public void setSearchTime(LocalDateTime searchTime) { this.searchTime = searchTime; }
    public String getFilters() { return filters; }
    public void setFilters(String filters) { this.filters = filters; }
}
