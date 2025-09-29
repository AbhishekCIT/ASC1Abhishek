package com.example.airtransport.controller;

import com.example.airtransport.model.*;
import com.example.airtransport.service.*;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controller for booking-related APIs: search flights, book, pay, send email.
 */
@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
@Validated
public class BookingController {
    private final FlightSearchService flightSearchService;
    private final BookingService bookingService;
    private final PaymentService paymentService;
    private final NotificationService notificationService;

    /**
     * Search for available flights.
     */
    @GetMapping("/flights/search")
    public ResponseEntity<FlightSearchResponse> searchFlights(@Valid @RequestBody FlightSearchRequest request) {
        List<FlightDTO> flights = flightSearchService.searchFlights(request);
        return ResponseEntity.ok(new FlightSearchResponse(flights));
    }

    /**
     * Book a flight and return booking confirmation.
     */
    @PostMapping("/bookings")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<BookingResponse> bookFlight(@Valid @RequestBody BookingRequest request) {
        BookingResponse response = bookingService.bookFlight(request);
        return ResponseEntity.ok(response);
    }

    /**
     * Process payment for a booking.
     */
    @PostMapping("/payments")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<PaymentResponse> processPayment(@Valid @RequestBody PaymentRequest request) {
        PaymentResponse response = paymentService.processPayment(request);
        return ResponseEntity.ok(response);
    }

    /**
     * Send booking confirmation email.
     */
    @PostMapping("/notifications/email")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<EmailResponse> sendConfirmationEmail(@Valid @RequestBody EmailRequest request) {
        EmailResponse response = notificationService.sendBookingConfirmationEmail(request);
        return ResponseEntity.ok(response);
    }
}
