package com.example.flightsearch.util;

import com.example.flightsearch.exception.InvalidInputException;
import com.example.flightsearch.model.FlightSearchRequest;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

/**
 * Utility class for validating flight search parameters.
 */
@Component
public class ValidationUtil {
    /**
     * Validates the flight search request parameters.
     * @param request FlightSearchRequest
     * @throws InvalidInputException if any validation fails
     */
    public void validateSearchParams(FlightSearchRequest request) {
        if (request.getOrigin() == null || request.getOrigin().trim().isEmpty()) {
            throw new InvalidInputException("Origin must not be empty");
        }
        if (request.getDestination() == null || request.getDestination().trim().isEmpty()) {
            throw new InvalidInputException("Destination must not be empty");
        }
        try {
            LocalDate depDate = LocalDate.parse(request.getDepartureDate());
            if (depDate.isBefore(LocalDate.now())) {
                throw new InvalidInputException("Departure date must be in the future");
            }
            if (request.getReturnDate() != null && !request.getReturnDate().isEmpty()) {
                LocalDate retDate = LocalDate.parse(request.getReturnDate());
                if (retDate.isBefore(depDate)) {
                    throw new InvalidInputException("Return date must be after departure date");
                }
            }
        } catch (DateTimeParseException e) {
            throw new InvalidInputException("Invalid date format. Use YYYY-MM-DD");
        }
        // Additional filter validations can be added here
    }
}
