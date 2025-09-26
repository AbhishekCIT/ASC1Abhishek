package com.airtransport.service;

import com.airtransport.model.FlightResponse;
import com.airtransport.util.DateUtil;
import com.airtransport.exception.InvalidInputException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import java.util.List;

/**
 * FlightSearchService provides business logic for searching flights.
 */
@Service
public class FlightSearchService {
    @Autowired
    private AirlineAPIClient airlineAPIClient;

    /**
     * Search flights by origin, destination, date, and airline.
     * Validates input and delegates to AirlineAPIClient.
     */
    @Cacheable("flightSearch")
    public List<FlightResponse> searchFlights(String origin, String destination, String date, String airline) {
        if (!DateUtil.isFutureDate(date)) {
            throw new InvalidInputException("Invalid travel date");
        }
        if (!airlineAPIClient.isSupportedDestination(destination)) {
            throw new InvalidInputException("Invalid destination");
        }
        return airlineAPIClient.queryFlights(origin, destination, date, airline);
    }
}
