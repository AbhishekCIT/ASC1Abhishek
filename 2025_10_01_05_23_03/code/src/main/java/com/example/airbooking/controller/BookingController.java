package com.example.airbooking.controller;

import com.example.airbooking.dto.BookingRequest;
import com.example.airbooking.dto.BookingResponse;
import com.example.airbooking.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * REST controller for booking APIs.
 */
@RestController
@RequestMapping("/api/bookings")
public class BookingController {
    @Autowired
    private BookingService bookingService;

    /**
     * Book a flight.
     * @param request Booking request
     * @return Booking confirmation or error
     */
    @PostMapping
    public ResponseEntity<BookingResponse> bookFlight(@RequestBody BookingRequest request) {
        BookingResponse response = bookingService.createBooking(request);
        return ResponseEntity.ok(response);
    }

    /**
     * Get booking details by booking ID.
     * @param bookingId Booking ID
     * @return Booking details
     */
    @GetMapping("/{bookingId}")
    public ResponseEntity<BookingResponse> getBooking(@PathVariable Integer bookingId) {
        BookingResponse response = bookingService.getBooking(bookingId);
        return ResponseEntity.ok(response);
    }
}
