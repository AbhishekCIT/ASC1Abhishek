package com.example.flightsearch.util;

import com.example.flightsearch.exception.InvalidAirportCodeException;
import com.example.flightsearch.exception.InvalidDateException;
import com.example.flightsearch.exception.InvalidPassengerCountException;
import com.example.flightsearch.model.FlightSearchRequest;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * Utility service for validating search parameters.
 */
@Component
public class ValidationService {
    // Example set of valid airport codes
    private static final Set<String> VALID_AIRPORT_CODES = new HashSet<>(Arrays.asList("JFK", "LAX", "ORD", "ATL", "DFW", "DEN", "SFO", "SEA", "MIA"));
    private static final int MAX_PASSENGERS = 9;

    /**
     * Validates the search parameters for flight search.
     * @param request Flight search request
     */
    public void validateSearchParams(FlightSearchRequest request) {
        if (request.getOrigin() == null || !VALID_AIRPORT_CODES.contains(request.getOrigin().toUpperCase())) {
            throw new InvalidAirportCodeException("Invalid airport code for origin");
        }
        if (request.getDestination() == null || !VALID_AIRPORT_CODES.contains(request.getDestination().toUpperCase())) {
            throw new InvalidAirportCodeException("Invalid airport code for destination");
        }
        if (request.getDate() == null || request.getDate().isBefore(LocalDate.now())) {
            throw new InvalidDateException("Travel date cannot be in the past");
        }
        if (request.getPassengers() < 1 || request.getPassengers() > MAX_PASSENGERS) {
            throw new InvalidPassengerCountException("Invalid passenger count");
        }
    }
}
