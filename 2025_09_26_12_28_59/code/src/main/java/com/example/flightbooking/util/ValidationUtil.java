package com.example.flightbooking.util;

import com.example.flightbooking.model.FlightSearchRequest;
import com.example.flightbooking.model.BookingRequest;
import java.time.LocalDate;

/**
 * Utility for input validation logic.
 */
public class ValidationUtil {

    public static void validateSearchRequest(FlightSearchRequest request) {
        validateSearchParams(request.getOrigin(), request.getDestination(), request.getDate());
    }

    public static void validateSearchParams(String origin, String destination, LocalDate date) {
        if (origin == null || origin.trim().isEmpty()) {
            throw new IllegalArgumentException("Origin cannot be empty");
        }
        if (destination == null || destination.trim().isEmpty()) {
            throw new IllegalArgumentException("Destination cannot be empty");
        }
        if (date == null || !date.isAfter(LocalDate.now())) {
            throw new IllegalArgumentException("Travel date must be in the future");
        }
    }

    public static void validateBookingRequest(BookingRequest request) {
        if (request.getFlightId() == null || request.getFlightId().trim().isEmpty()) {
            throw new IllegalArgumentException("Flight ID cannot be empty");
        }
        if (request.getUserId() == null || request.getUserId().trim().isEmpty()) {
            throw new IllegalArgumentException("User ID cannot be empty");
        }
        if (request.getPaymentInfo() == null) {
            throw new IllegalArgumentException("Payment info is required");
        }
    }
}
