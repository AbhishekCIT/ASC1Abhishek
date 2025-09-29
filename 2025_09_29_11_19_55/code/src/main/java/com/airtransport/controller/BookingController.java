package com.airtransport.controller;

import com.airtransport.model.Booking;
import com.airtransport.model.BookingRequest;
import com.airtransport.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * REST Controller for booking flights and retrieving booking info.
 */
@RestController
@RequestMapping("/api/bookings")
public class BookingController {

    @Autowired
    private BookingService bookingService;

    /**
     * Book a flight.
     * @param bookingRequest Booking request payload
     * @return Booking confirmation
     */
    @PostMapping("/book")
    public Booking bookFlight(@RequestBody BookingRequest bookingRequest) {
        // Delegate to service for booking logic
        return bookingService.bookFlight(bookingRequest);
    }

    /**
     * Get booking details by bookingId.
     * @param bookingId Booking ID
     * @return Booking details
     */
    @GetMapping("/{bookingId}")
    public Booking getBooking(@PathVariable String bookingId) {
        return bookingService.getBooking(bookingId);
    }
}
