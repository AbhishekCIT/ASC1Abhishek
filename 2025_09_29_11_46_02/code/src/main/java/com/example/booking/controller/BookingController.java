package com.example.booking.controller;

import com.example.booking.model.BookingRequest;
import com.example.booking.model.BookingResponse;
import com.example.booking.service.BookingService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;

/**
 * REST controller for booking flights.
 * Handles POST requests to /api/bookings
 */
@RestController
@RequestMapping("/api/bookings")
public class BookingController {
    private static final Logger logger = LoggerFactory.getLogger(BookingController.class);

    @Autowired
    private BookingService bookingService;

    /**
     * Book a flight with passenger details, seat selection, and payment info.
     * @param request Booking request
     * @return Booking confirmation or error
     */
    @PostMapping
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<?> bookFlight(@Valid @RequestBody BookingRequest request) {
        logger.info("Booking request received: {}", request);
        try {
            BookingResponse response = bookingService.createBooking(request);
            logger.info("Booking successful: {}", response.getBookingReference());
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            logger.error("Booking failed: {}", e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorResponse(e.getMessage()));
        }
    }

    /**
     * Simple error response model
     */
    static class ErrorResponse {
        private String error;
        public ErrorResponse(String error) { this.error = error; }
        public String getError() { return error; }
        public void setError(String error) { this.error = error; }
    }
}
