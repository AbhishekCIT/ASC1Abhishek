package com.example.airlinebooking.controller;

import com.example.airlinebooking.model.Booking;
import com.example.airlinebooking.model.BookingRequest;
import com.example.airlinebooking.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Controller for managing booking requests.
 */
@RestController
@RequestMapping("/api/flights")
public class BookingController {
    @Autowired
    private BookingService bookingService;

    /**
     * Book a flight for a user.
     * @param bookingRequest the booking request payload
     * @return booking confirmation or error
     */
    @PostMapping("/book")
    public ResponseEntity<?> bookFlight(@RequestBody BookingRequest bookingRequest) {
        try {
            Booking booking = bookingService.createBooking(bookingRequest);
            return ResponseEntity.ok(booking);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("{\"error\":\"" + e.getMessage() + "\"}");
        }
    }

    /**
     * Get booking info by booking reference.
     * @param bookingRef booking reference
     * @return booking info
     */
    @GetMapping("/booking/{bookingRef}")
    public ResponseEntity<?> getBookingInfo(@PathVariable String bookingRef) {
        try {
            Booking booking = bookingService.getBookingByReference(bookingRef);
            return ResponseEntity.ok(booking);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("{\"error\":\"" + e.getMessage() + "\"}");
        }
    }
}
