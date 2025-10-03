package com.example.airtransport.service;

import com.example.airtransport.model.FlightSearchCriteria;
import com.example.airtransport.model.FlightSearchResponse;
import com.example.airtransport.model.Flight;
import com.example.airtransport.repository.FlightRepository;
import com.example.airtransport.util.PaginationUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Service for business logic of searching flights.
 */
@Service
public class FlightSearchService {
    @Autowired
    private FlightRepository flightRepository;
    @Autowired
    private PaginationUtil paginationUtil;

    /**
     * Searches for flights based on criteria and returns paginated results.
     * @param criteria Search criteria
     * @return Paginated flight search response
     */
    public FlightSearchResponse searchFlights(FlightSearchCriteria criteria) {
        // Query flights from repository
        List<Flight> flights = flightRepository.findFlights(criteria);
        // Filter by airline, price, stops if provided
        List<Flight> filtered = flights.stream()
            .filter(f -> criteria.getAirlines() == null || criteria.getAirlines().isEmpty() || criteria.getAirlines().contains(f.getAirline()))
            .filter(f -> criteria.getPriceRange() == null || (f.getPrice() >= criteria.getPriceRange().getMin() && f.getPrice() <= criteria.getPriceRange().getMax()))
            .filter(f -> criteria.getStops() == null || f.getLayovers() == criteria.getStops())
            .collect(Collectors.toList());
        // Paginate
        List<Flight> paginated = paginationUtil.paginate(filtered, criteria.getPage(), criteria.getSize());
        return new FlightSearchResponse(paginated, filtered.size(), criteria.getPage(), criteria.getSize());
    }
}
