package com.example.airtransport.util;

import com.example.airtransport.model.FlightSearchRequest;
import org.springframework.stereotype.Component;
import java.time.LocalDate;

/**
 * Utility class for validating flight search input.
 */
@Component
public class ValidationUtil {
    /**
     * Validates the search criteria for flight search.
     * Throws IllegalArgumentException if invalid.
     * @param request Flight search request
     */
    public void validateSearchCriteria(FlightSearchRequest request) {
        if (request.getOrigin() == null || request.getOrigin().isEmpty() ||
            request.getDestination() == null || request.getDestination().isEmpty() ||
            request.getDepartureDate() == null || request.getDepartureDate().isEmpty()) {
            throw new IllegalArgumentException("All mandatory fields must be filled.");
        }
        if (request.getOrigin().equalsIgnoreCase(request.getDestination())) {
            throw new IllegalArgumentException("Origin and destination cannot be the same.");
        }
        LocalDate depDate = LocalDate.parse(request.getDepartureDate());
        if (depDate.isBefore(LocalDate.now())) {
            throw new IllegalArgumentException("Travel dates must be in the future.");
        }
        if (request.getReturnDate() != null && !request.getReturnDate().isEmpty()) {
            LocalDate retDate = LocalDate.parse(request.getReturnDate());
            if (retDate.isBefore(depDate)) {
                throw new IllegalArgumentException("Return date must be after departure date.");
            }
        }
    }
}
