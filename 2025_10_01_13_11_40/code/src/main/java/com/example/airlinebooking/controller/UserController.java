package com.example.airlinebooking.controller;

import com.example.airlinebooking.model.*;
import com.example.airlinebooking.service.FlightService;
import com.example.airlinebooking.service.PaymentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * Controller to handle user requests for flight booking APIs.
 */
@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class UserController {
    private final FlightService flightService;
    private final PaymentService paymentService;

    /**
     * Search available flights.
     */
    @GetMapping("/flights/search")
    public ResponseEntity<List<FlightSearchResponse>> searchFlights(@Valid @RequestBody FlightSearchRequest request) {
        return ResponseEntity.ok(flightService.searchFlights(request));
    }

    /**
     * View flight details.
     */
    @GetMapping("/flights/{flightId}")
    public ResponseEntity<Map<String, Object>> viewFlight(@PathVariable String flightId) {
        return ResponseEntity.ok(flightService.viewFlight(flightId));
    }

    /**
     * Book a ticket.
     */
    @PostMapping("/bookings")
    public ResponseEntity<BookTicketResponse> bookTicket(@Valid @RequestBody BookTicketRequest request) {
        return ResponseEntity.ok(flightService.bookTicket(request));
    }

    /**
     * Process payment for a booking.
     */
    @PostMapping("/payments")
    public ResponseEntity<PaymentResponse> processPayment(@Valid @RequestBody PaymentRequest request) {
        return ResponseEntity.ok(paymentService.processPayment(request));
    }

    /**
     * Get booking confirmation.
     */
    @GetMapping("/bookings/{bookingId}/confirmation")
    public ResponseEntity<BookingConfirmationResponse> getBookingConfirmation(@PathVariable String bookingId) {
        return ResponseEntity.ok(flightService.getBookingConfirmation(bookingId));
    }
}
