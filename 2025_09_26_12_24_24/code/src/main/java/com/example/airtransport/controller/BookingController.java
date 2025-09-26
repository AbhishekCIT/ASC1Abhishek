package com.example.airtransport.controller;

import com.example.airtransport.model.Booking;
import com.example.airtransport.model.BookingRequest;
import com.example.airtransport.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Controller for handling booking creation and retrieval endpoints.
 */
@RestController
@RequestMapping("/api/flights")
public class BookingController {
    @Autowired
    private BookingService bookingService;

    /**
     * Create a new booking for a flight.
     * @param bookingRequest Booking request payload
     * @return Booking confirmation details
     */
    @PostMapping("/book")
    public ResponseEntity<Booking> bookFlight(@RequestBody BookingRequest bookingRequest) {
        Booking booking = bookingService.createBooking(bookingRequest);
        return ResponseEntity.ok(booking);
    }

    /**
     * Retrieve booking information by booking reference.
     * @param reference Booking reference
     * @return Booking details
     */
    @GetMapping("/booking/{reference}")
    public ResponseEntity<Booking> getBookingInfo(@PathVariable String reference) {
        Booking booking = bookingService.getBookingByReference(reference);
        return ResponseEntity.ok(booking);
    }
}
