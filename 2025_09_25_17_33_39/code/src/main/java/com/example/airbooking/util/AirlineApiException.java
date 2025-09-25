package com.example.airbooking.util;

/**
 * Thrown when there is an error communicating with the airline API.
 */
public class AirlineApiException extends RuntimeException {
    public AirlineApiException(String message) {
        super(message);
    }
}
