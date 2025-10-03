package com.example.airbooking.controller;

import com.example.airbooking.model.Booking;
import com.example.airbooking.model.BookingRequest;
import com.example.airbooking.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Controller for handling booking operations.
 */
@RestController
@RequestMapping("/api/bookings")
public class BookingController {

    @Autowired
    private BookingService bookingService;

    /**
     * Book a flight with passenger and payment details.
     * @param bookingRequest Booking request payload
     * @return Booking confirmation or error
     */
    @PostMapping
    public ResponseEntity<?> bookFlight(@RequestBody BookingRequest bookingRequest) {
        try {
            Booking booking = bookingService.createBooking(bookingRequest);
            return ResponseEntity.ok(booking);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("{\"error\":\"" + e.getMessage() + "\"}");
        }
    }
}
