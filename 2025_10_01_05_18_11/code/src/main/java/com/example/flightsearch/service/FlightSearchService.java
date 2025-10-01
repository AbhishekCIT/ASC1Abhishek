package com.example.flightsearch.service;

import com.example.flightsearch.client.FlightDataProviderClient;
import com.example.flightsearch.exception.ExternalAPIException;
import com.example.flightsearch.exception.NoFlightsFoundException;
import com.example.flightsearch.logger.SearchQueryLogger;
import com.example.flightsearch.model.Flight;
import com.example.flightsearch.model.FlightSearchRequest;
import com.example.flightsearch.model.FlightSearchResponse;
import com.example.flightsearch.repository.SearchQueryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Service class for flight search business logic.
 */
@Service
public class FlightSearchService {

    @Autowired
    private FlightDataProviderClient flightDataProviderClient;

    @Autowired
    private SearchQueryLogger searchQueryLogger;

    @Autowired
    private SearchQueryRepository searchQueryRepository;

    /**
     * Searches for flights based on user request.
     * @param request Flight search request
     * @return FlightSearchResponse containing available flights
     */
    public FlightSearchResponse searchFlights(FlightSearchRequest request) {
        try {
            // Fetch flights from airline data provider
            List<Flight> flights = flightDataProviderClient.fetchFlights(request);

            // Apply filters (airline, price, stops, etc.)
            if (request.getFilters() != null) {
                if (request.getFilters().getAirline() != null) {
                    flights = flights.stream().filter(f -> f.getAirline().equalsIgnoreCase(request.getFilters().getAirline())).collect(Collectors.toList());
                }
                if (request.getFilters().getMaxPrice() != null) {
                    flights = flights.stream().filter(f -> f.getPrice() <= request.getFilters().getMaxPrice()).collect(Collectors.toList());
                }
                if (request.getFilters().getStops() != null) {
                    flights = flights.stream().filter(f -> f.getStops() <= request.getFilters().getStops()).collect(Collectors.toList());
                }
            }

            // Sort results if requested
            if (request.getSortBy() != null) {
                switch (request.getSortBy()) {
                    case "price":
                        flights = flights.stream().sorted((a, b) -> Double.compare(a.getPrice(), b.getPrice())).collect(Collectors.toList());
                        break;
                    case "duration":
                        flights = flights.stream().sorted((a, b) -> Long.compare(a.getDurationMinutes(), b.getDurationMinutes())).collect(Collectors.toList());
                        break;
                    case "departure":
                        flights = flights.stream().sorted((a, b) -> a.getDeparture().compareTo(b.getDeparture())).collect(Collectors.toList());
                        break;
                }
            }

            // Only return flights with available seats
            flights = flights.stream().filter(f -> f.getAvailableSeats() > 0).collect(Collectors.toList());

            if (flights.isEmpty()) {
                throw new NoFlightsFoundException("No flights found matching your criteria.");
            }

            // Log the search query for analytics
            searchQueryLogger.log(request);

            // Build response
            FlightSearchResponse response = new FlightSearchResponse();
            response.setFlights(flights);
            return response;
        } catch (ExternalAPIException e) {
            throw e;
        }
    }
}
