package com.example.airbooking.controller;

import com.example.airbooking.model.*;
import com.example.airbooking.service.*;
import com.example.airbooking.util.ValidationUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.access.prepost.PreAuthorize;
import java.util.List;

/**
 * Controller for flight search and booking APIs
 */
@RestController
@RequestMapping("/api")
public class FlightController {

    @Autowired
    private FlightSearchService flightSearchService;

    @Autowired
    private BookingService bookingService;

    @Autowired
    private PaymentService paymentService;

    @Autowired
    private ValidationUtil validationUtil;

    /**
     * Search for available flights
     * @param searchRequest flight search criteria
     * @return list of available flights
     */
    @PreAuthorize("hasRole('TRAVELER')")
    @GetMapping("/flights/search")
    public ResponseEntity<List<FlightResponse>> searchFlights(@RequestBody FlightSearchRequest searchRequest) {
        validationUtil.validateFlightSearchRequest(searchRequest);
        List<FlightResponse> flights = flightSearchService.searchFlights(searchRequest);
        return ResponseEntity.ok(flights);
    }

    /**
     * Book a flight
     * @param bookingRequest booking details
     * @return booking confirmation
     */
    @PreAuthorize("hasRole('TRAVELER')")
    @PostMapping("/bookings")
    public ResponseEntity<?> bookFlight(@RequestBody BookingRequest bookingRequest) {
        try {
            validationUtil.validateBookingRequest(bookingRequest);
            BookingConfirmation confirmation = bookingService.createBooking(bookingRequest);
            return ResponseEntity.ok(confirmation);
        } catch (Exception ex) {
            return ResponseEntity.badRequest().body(new ErrorResponse(ex.getMessage()));
        }
    }

    /**
     * Process payment for a booking
     * @param paymentRequest payment details
     * @return payment status
     */
    @PreAuthorize("hasRole('TRAVELER')")
    @PostMapping("/payments")
    public ResponseEntity<?> processPayment(@RequestBody PaymentRequest paymentRequest) {
        try {
            validationUtil.validatePaymentRequest(paymentRequest);
            PaymentResponse paymentResponse = paymentService.processPayment(paymentRequest);
            return ResponseEntity.ok(paymentResponse);
        } catch (Exception ex) {
            return ResponseEntity.badRequest().body(new ErrorResponse(ex.getMessage()));
        }
    }

    /**
     * Get booking confirmation and itinerary
     * @param bookingId booking identifier
     * @return booking confirmation and itinerary
     */
    @PreAuthorize("hasRole('TRAVELER')")
    @GetMapping("/bookings/{bookingId}/confirm")
    public ResponseEntity<?> getBookingConfirmation(@PathVariable String bookingId) {
        try {
            BookingConfirmation confirmation = bookingService.getBookingConfirmation(bookingId);
            return ResponseEntity.ok(confirmation);
        } catch (Exception ex) {
            return ResponseEntity.badRequest().body(new ErrorResponse(ex.getMessage()));
        }
    }
}
