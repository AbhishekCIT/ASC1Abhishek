package com.example.airbooking.util;

import com.example.airbooking.model.*;
import com.example.airbooking.exception.InvalidInputException;
import org.springframework.stereotype.Component;
import java.time.LocalDate;

/**
 * Utility for validating API requests
 */
@Component
public class ValidationUtil {
    public void validateFlightSearchRequest(FlightSearchRequest req) {
        if (req.getOrigin() == null || req.getOrigin().isEmpty()) {
            throw new InvalidInputException("Origin is required");
        }
        if (req.getDestination() == null || req.getDestination().isEmpty()) {
            throw new InvalidInputException("Destination is required");
        }
        if (req.getDepartureDate() == null || req.getDepartureDate().isBefore(LocalDate.now())) {
            throw new InvalidInputException("Invalid departure date");
        }
    }
    public void validateBookingRequest(BookingRequest req) {
        if (req.getFlightId() == null || req.getFlightId().isEmpty()) {
            throw new InvalidInputException("Flight ID is required");
        }
        if (req.getPassengerInfo() == null) {
            throw new InvalidInputException("Passenger info incomplete");
        }
        // Add more validations as needed
    }
    public void validatePaymentRequest(PaymentRequest req) {
        if (req.getBookingId() == null || req.getBookingId().isEmpty()) {
            throw new InvalidInputException("Booking ID is required");
        }
        if (req.getPaymentInfo() == null) {
            throw new InvalidInputException("Payment information invalid");
        }
    }
}
