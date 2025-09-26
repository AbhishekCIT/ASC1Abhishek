package com.example.airbooking.util;

import com.example.airbooking.model.SearchCriteria;
import com.example.airbooking.model.BookingRequest;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

/**
 * Utility class for input and business validations.
 */
@Component
public class ValidationUtils {
    /**
     * Validates search criteria for flight search.
     * @param criteria SearchCriteria
     */
    public void validateSearchCriteria(SearchCriteria criteria) {
        if (criteria.getOrigin() == null || criteria.getDestination() == null || criteria.getOrigin().equals(criteria.getDestination())) {
            throw new IllegalArgumentException("Origin and destination cannot be the same");
        }
        if (criteria.getDepartureDate() == null || criteria.getDepartureDate().isBefore(LocalDate.now())) {
            throw new IllegalArgumentException("Departure date cannot be in the past");
        }
    }

    /**
     * Validates booking request.
     * @param request BookingRequest
     */
    public void validateBookingRequest(BookingRequest request) {
        if (request.getFlightId() == null || request.getUserId() == null || request.getPassengerInfo() == null || request.getPaymentInfo() == null) {
            throw new IllegalArgumentException("Incomplete booking request");
        }
    }
}
