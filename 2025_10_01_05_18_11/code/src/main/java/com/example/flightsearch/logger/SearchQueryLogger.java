package com.example.flightsearch.logger;

import com.example.flightsearch.model.FlightSearchRequest;
import com.example.flightsearch.entity.SearchQuery;
import com.example.flightsearch.repository.SearchQueryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * Logs search queries for analytics and auditing.
 */
@Component
public class SearchQueryLogger {

    @Autowired
    private SearchQueryRepository searchQueryRepository;

    /**
     * Logs the search query to the database.
     * @param request Flight search request
     */
    public void log(FlightSearchRequest request) {
        SearchQuery query = new SearchQuery();
        query.setUserId(request.getUserId());
        query.setOrigin(request.getOrigin());
        query.setDestination(request.getDestination());
        query.setDepartureDate(request.getDepartureDate());
        query.setReturnDate(request.getReturnDate());
        query.setFilters(request.getFilters() != null ? request.getFilters().toString() : null);
        query.setSearchTime(LocalDateTime.now());
        searchQueryRepository.save(query);
    }
}
