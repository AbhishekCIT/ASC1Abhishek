package com.example.booking.exception;

/**
 * Thrown if passenger details are incomplete/invalid.
 */
public class InvalidPassengerDetailsException extends RuntimeException {
    public InvalidPassengerDetailsException(String message) { super(message); }
}
