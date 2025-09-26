package com.example.flightbooking.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Exception for travel date in the past.
 */
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class DateInPastException extends RuntimeException {
    public DateInPastException(String message) {
        super(message);
    }
}
