package com.example.flightsearch.util;

import com.example.flightsearch.model.FlightSearchRequest;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

/**
 * Utility service to validate flight search input criteria.
 */
@Component
public class ValidationService {
    /**
     * Validates search criteria fields.
     * @param request Flight search request
     * @throws IllegalArgumentException if validation fails
     */
    public void validateSearchCriteria(FlightSearchRequest request) {
        if (request.getOrigin() == null || request.getOrigin().trim().isEmpty() ||
            request.getDestination() == null || request.getDestination().trim().isEmpty()) {
            throw new IllegalArgumentException("Origin and destination must not be empty.");
        }
        try {
            LocalDate departure = LocalDate.parse(request.getDepartureDate());
            if (departure.isBefore(LocalDate.now())) {
                throw new IllegalArgumentException("Travel dates must not be in the past.");
            }
            if (request.getReturnDate() != null && !request.getReturnDate().isEmpty()) {
                LocalDate returnDate = LocalDate.parse(request.getReturnDate());
                if (returnDate.isBefore(departure)) {
                    throw new IllegalArgumentException("Return date must be after departure date.");
                }
            }
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("Invalid date format. Use yyyy-MM-dd.");
        }
    }
}
