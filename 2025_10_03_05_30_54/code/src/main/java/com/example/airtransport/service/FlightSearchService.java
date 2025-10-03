package com.example.airtransport.service;

import com.example.airtransport.model.FlightSearchRequest;
import com.example.airtransport.model.FlightSearchResponse;
import com.example.airtransport.util.ValidationUtil;
import com.example.airtransport.util.SearchQueryLogger;
import com.example.airtransport.external.FlightAPIClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDate;

/**
 * Service class for flight search business logic.
 */
@Service
public class FlightSearchService {
    @Autowired
    private FlightAPIClient flightAPIClient;
    @Autowired
    private ValidationUtil validationUtil;
    @Autowired
    private SearchQueryLogger searchQueryLogger;

    /**
     * Searches for flights based on criteria and applies filtering/sorting.
     * @param request Flight search criteria
     * @return Search results
     */
    public FlightSearchResponse searchFlights(FlightSearchRequest request) {
        // Validate input
        validationUtil.validateSearchCriteria(request);

        // Fetch flights from external API
        FlightSearchResponse response = flightAPIClient.fetchFlights(request);

        // Log the search query for analytics
        searchQueryLogger.logQuery(request, response);

        return response;
    }
}
