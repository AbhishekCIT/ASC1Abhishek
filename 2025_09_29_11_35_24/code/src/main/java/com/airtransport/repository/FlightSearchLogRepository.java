package com.airtransport.repository;

import com.airtransport.model.Flight;
import com.airtransport.model.FlightSearchRequest;
import com.airtransport.entity.SearchQuery;
import com.airtransport.entity.FlightResult;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * Repository for logging flight search queries and results.
 * In production, this would use a JPA repository and persist to a database.
 */
@Repository
public class FlightSearchLogRepository {
    /**
     * Saves the search query and associated flight results for audit/logging.
     * @param request Flight search request
     * @param flights List of flights returned
     */
    public void saveSearchResult(FlightSearchRequest request, List<Flight> flights) {
        // In production, persist to DB. Here, just a stub for demonstration.
        SearchQuery query = new SearchQuery();
        query.setId(UUID.randomUUID().toString());
        query.setUserId(request.getUserId());
        query.setSource(request.getSource());
        query.setDestination(request.getDestination());
        query.setTravelDate(request.getDate());
        query.setPassengerCount(request.getPassengerCount());
        query.setTimestamp(LocalDateTime.now());
        // Save query and results
        List<FlightResult> results = flights.stream().map(flight -> {
            FlightResult fr = new FlightResult();
            fr.setId(UUID.randomUUID().toString());
            fr.setSearchQueryId(query.getId());
            fr.setAirline(flight.getAirline());
            fr.setFlightNumber(flight.getFlightNumber());
            fr.setDepartureTime(flight.getDepartureTime());
            fr.setArrivalTime(flight.getArrivalTime());
            fr.setDuration(flight.getDuration());
            fr.setPrice(flight.getPrice());
            return fr;
        }).collect(Collectors.toList());
        // TODO: Persist query and results to DB
    }
}
