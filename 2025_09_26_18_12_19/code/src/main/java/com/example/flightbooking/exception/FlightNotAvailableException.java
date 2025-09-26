package com.example.flightbooking.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Exception for unavailable flights.
 */
@ResponseStatus(HttpStatus.NOT_FOUND)
public class FlightNotAvailableException extends RuntimeException {
    public FlightNotAvailableException(String message) {
        super(message);
    }
}
