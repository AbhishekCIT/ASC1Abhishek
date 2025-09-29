package com.airbook.controller;

import com.airbook.model.Booking;
import com.airbook.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * REST controller for booking operations
 */
@RestController
@RequestMapping("/api/bookings")
public class BookingController {

    @Autowired
    private BookingService bookingService;

    /**
     * Book a flight
     * @param booking Booking request payload
     * @return Booking confirmation
     */
    @PostMapping
    public ResponseEntity<Booking> bookFlight(@RequestBody Booking booking) {
        Booking confirmedBooking = bookingService.createBooking(booking);
        return ResponseEntity.ok(confirmedBooking);
    }

    /**
     * Get booking confirmation by booking ID
     * @param bookingId Booking ID
     * @return Booking confirmation
     */
    @GetMapping("/{bookingId}")
    public ResponseEntity<Booking> getBookingConfirmation(@PathVariable String bookingId) {
        Booking booking = bookingService.getBookingConfirmation(bookingId);
        return ResponseEntity.ok(booking);
    }
}
