package com.example.airtransport.util;

import com.example.airtransport.model.FlightSearchRequest;
import com.example.airtransport.model.FlightSearchResponse;
import com.example.airtransport.entity.SearchQuery;
import com.example.airtransport.repository.SearchQueryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.time.LocalDateTime;

/**
 * Utility class to log search queries for analytics and auditing.
 */
@Component
public class SearchQueryLogger {
    @Autowired
    private SearchQueryRepository searchQueryRepository;

    /**
     * Logs the search query and response summary.
     * @param request Flight search request
     * @param response Flight search response
     */
    public void logQuery(FlightSearchRequest request, FlightSearchResponse response) {
        SearchQuery query = new SearchQuery();
        query.setUserId(request.getUserId());
        query.setOrigin(request.getOrigin());
        query.setDestination(request.getDestination());
        query.setDepartureDate(request.getDepartureDate());
        query.setReturnDate(request.getReturnDate());
        query.setTimestamp(LocalDateTime.now());
        searchQueryRepository.save(query);
    }
}
