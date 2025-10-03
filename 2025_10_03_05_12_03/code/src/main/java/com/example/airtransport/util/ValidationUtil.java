package com.example.airtransport.util;

import com.example.airtransport.model.FlightSearchCriteria;
import org.springframework.stereotype.Component;
import java.time.LocalDate;

/**
 * Utility for validating input criteria for flight search.
 */
@Component
public class ValidationUtil {
    /**
     * Validates the flight search criteria.
     * Throws IllegalArgumentException if invalid.
     * @param criteria Search criteria
     */
    public void validateSearchCriteria(FlightSearchCriteria criteria) {
        if (criteria.getOrigin() == null || criteria.getOrigin().isEmpty() ||
            criteria.getDestination() == null || criteria.getDestination().isEmpty()) {
            throw new IllegalArgumentException("All required fields must be filled");
        }
        if (criteria.getOrigin().equalsIgnoreCase(criteria.getDestination())) {
            throw new IllegalArgumentException("Origin and destination cannot be the same");
        }
        LocalDate today = LocalDate.now();
        if (criteria.getDepartureDate() == null || criteria.getDepartureDate().isBefore(today)) {
            throw new IllegalArgumentException("Departure date is invalid");
        }
        if (criteria.getReturnDate() != null && criteria.getReturnDate().isBefore(today)) {
            throw new IllegalArgumentException("Return date is invalid");
        }
    }
}
