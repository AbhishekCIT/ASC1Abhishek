package com.example.airbooking.controller;

import com.example.airbooking.model.*;
import com.example.airbooking.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

/**
 * Handles user requests for flight search, booking, and payment operations.
 */
@RestController
@RequestMapping("/api")
public class UserController {
    @Autowired
    private FlightSearchService flightSearchService;
    @Autowired
    private BookingService bookingService;
    @Autowired
    private PaymentService paymentService;

    /**
     * Search available flights by origin, destination, and date.
     */
    @GetMapping("/flights/search")
    public ResponseEntity<List<Flight>> searchFlights(@RequestParam String origin,
                                                     @RequestParam String destination,
                                                     @RequestParam String date) {
        List<Flight> flights = flightSearchService.searchFlights(origin, destination, date);
        return ResponseEntity.ok(flights);
    }

    /**
     * Book a flight with passenger and payment details.
     */
    @PostMapping("/bookings")
    public ResponseEntity<BookingConfirmation> bookFlight(@RequestBody BookingRequest bookingRequest) {
        BookingConfirmation confirmation = bookingService.createBooking(bookingRequest);
        return ResponseEntity.ok(confirmation);
    }

    /**
     * Process payment for a booking.
     */
    @PostMapping("/payments")
    public ResponseEntity<PaymentResponse> processPayment(@RequestBody PaymentRequest paymentRequest) {
        PaymentResponse response = paymentService.processPayment(paymentRequest);
        return ResponseEntity.ok(response);
    }

    /**
     * Get booking details by booking ID.
     */
    @GetMapping("/bookings/{bookingId}")
    public ResponseEntity<BookingConfirmation> getBooking(@PathVariable Long bookingId) {
        BookingConfirmation confirmation = bookingService.getBooking(bookingId);
        return ResponseEntity.ok(confirmation);
    }
}
