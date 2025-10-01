package com.example.airbooking.exception;

import com.example.airbooking.dto.BookingResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * Global exception handler for REST APIs.
 */
@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler({FlightNotFoundException.class, InvalidInputException.class, PaymentFailedException.class, BookingNotFoundException.class})
    public ResponseEntity<BookingResponse> handleCustomExceptions(RuntimeException ex) {
        BookingResponse response = new BookingResponse();
        response.setError(ex.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }
}
