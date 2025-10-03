package com.example.airbooking.exception;

public class BookingOverbookedException extends RuntimeException {
    public BookingOverbookedException(String message) { super(message); }
}
