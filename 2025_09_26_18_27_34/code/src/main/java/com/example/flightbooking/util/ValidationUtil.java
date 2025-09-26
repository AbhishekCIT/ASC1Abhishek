package com.example.flightbooking.util;

import com.example.flightbooking.model.*;
import org.springframework.stereotype.Component;
import java.time.LocalDate;

/**
 * Utility class for validating search, booking, and payment requests.
 */
@Component
public class ValidationUtil {
    public void validateSearchCriteria(SearchCriteria criteria) {
        if (criteria.getOrigin().equalsIgnoreCase(criteria.getDestination())) {
            throw new IllegalArgumentException("Origin and destination cannot be the same");
        }
        if (criteria.getDepartureDate().isBefore(LocalDate.now())) {
            throw new IllegalArgumentException("Invalid travel dates");
        }
    }

    public void validateBookingRequest(BookingRequest request) {
        // Add booking-specific validations here
        if (request.getPassengerDetails() == null || request.getPassengerDetails().isEmpty()) {
            throw new IllegalArgumentException("Passenger details required");
        }
    }

    public void validatePaymentRequest(PaymentRequest request) {
        // Add PCI DSS and payment info validation here
        if (request.getPaymentInfo() == null) {
            throw new IllegalArgumentException("Invalid payment details");
        }
    }
}
