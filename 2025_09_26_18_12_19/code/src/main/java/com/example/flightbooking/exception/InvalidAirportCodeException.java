package com.example.flightbooking.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Exception for invalid airport codes.
 */
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class InvalidAirportCodeException extends RuntimeException {
    public InvalidAirportCodeException(String message) {
        super(message);
    }
}
