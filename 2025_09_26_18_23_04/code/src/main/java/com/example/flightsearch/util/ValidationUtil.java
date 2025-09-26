package com.example.flightsearch.util;

import com.example.flightsearch.model.FlightSearchRequest;
import org.springframework.stereotype.Component;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * Utility for validating flight search parameters.
 */
@Component
public class ValidationUtil {
    private static final Set<String> VALID_CLASSES = new HashSet<>(Arrays.asList("Economy", "Business", "First"));
    private static final Set<String> VALID_AIRPORT_CODES = new HashSet<>(Arrays.asList("JFK", "LAX", "ORD", "DFW", "SFO", "ATL", "DEN", "SEA", "MIA", "BOS")); // Example codes

    /**
     * Validates the search parameters and throws IllegalArgumentException if invalid.
     * @param request Flight search request
     */
    public void validateSearchParams(FlightSearchRequest request) {
        StringBuilder sb = new StringBuilder();
        if (request.getOrigin() == null || request.getOrigin().isEmpty() || !VALID_AIRPORT_CODES.contains(request.getOrigin())) {
            sb.append("Invalid origin; ");
        }
        if (request.getDestination() == null || request.getDestination().isEmpty() || !VALID_AIRPORT_CODES.contains(request.getDestination())) {
            sb.append("Invalid destination; ");
        }
        if (request.getOrigin() != null && request.getOrigin().equals(request.getDestination())) {
            sb.append("Origin and destination cannot be the same; ");
        }
        if (request.getDate() == null || request.getDate().isBefore(LocalDate.now())) {
            sb.append("Travel date cannot be in the past; ");
        }
        if (request.getPassengers() < 1) {
            sb.append("Passengers must be at least 1; ");
        }
        if (request.getFlightClass() == null || !VALID_CLASSES.contains(request.getFlightClass())) {
            sb.append("Invalid class of service; ");
        }
        if (sb.length() > 0) {
            throw new IllegalArgumentException(sb.toString());
        }
    }
}
