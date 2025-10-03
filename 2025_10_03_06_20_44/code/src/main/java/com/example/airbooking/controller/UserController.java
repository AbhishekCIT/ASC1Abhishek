package com.example.airbooking.controller;

import com.example.airbooking.model.*;
import com.example.airbooking.service.BookingService;
import com.example.airbooking.service.FlightSearchService;
import com.example.airbooking.service.PaymentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

/**
 * Controller for user-facing APIs: search, book, pay, confirm.
 */
@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class UserController {
    private final FlightSearchService flightSearchService;
    private final BookingService bookingService;
    private final PaymentService paymentService;

    /**
     * Search for flights.
     */
    @GetMapping("/flights/search")
    public ResponseEntity<List<FlightSearchResponse>> searchFlights(@Valid FlightSearchRequest request) {
        return ResponseEntity.ok(flightSearchService.searchFlights(request));
    }

    /**
     * Book a flight.
     */
    @PostMapping("/bookings")
    public ResponseEntity<BookingResponse> bookFlight(@Valid @RequestBody BookingRequest request) {
        return ResponseEntity.ok(bookingService.createBooking(request));
    }

    /**
     * Make payment for a booking.
     */
    @PostMapping("/payments")
    public ResponseEntity<PaymentResponse> pay(@Valid @RequestBody PaymentRequest request) {
        return ResponseEntity.ok(paymentService.processPayment(request));
    }

    /**
     * Confirm booking status.
     */
    @GetMapping("/bookings/{id}/confirm")
    public ResponseEntity<BookingConfirmResponse> confirmBooking(@PathVariable Integer id) {
        return ResponseEntity.ok(bookingService.confirmBooking(id));
    }
}
