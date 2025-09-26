package com.example.flightbooking.controller;

import com.example.flightbooking.model.*;
import com.example.flightbooking.service.*;
import com.example.flightbooking.util.ValidationUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;

/**
 * Controller for flight search and booking APIs.
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
     * Search available flights based on criteria.
     */
    @GetMapping("/flights/search")
    public ResponseEntity<?> searchFlights(@Valid @RequestBody SearchCriteria criteria) {
        try {
            validationUtil.validateSearchCriteria(criteria);
            List<Flight> flights = flightSearchService.searchFlights(criteria);
            return ResponseEntity.ok(new FlightSearchResponse(flights));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    /**
     * Book a flight and process payment.
     */
    @PostMapping("/bookings")
    public ResponseEntity<?> bookFlight(@Valid @RequestBody BookingRequest request, @RequestHeader("Authorization") String token) {
        try {
            validationUtil.validateBookingRequest(request);
            Booking booking = bookingService.bookFlight(request, token);
            return ResponseEntity.ok(new BookingResponse(booking));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    /**
     * Process payment for a booking.
     */
    @PostMapping("/payments/process")
    public ResponseEntity<?> processPayment(@Valid @RequestBody PaymentRequest request) {
        try {
            validationUtil.validatePaymentRequest(request);
            PaymentResult result = paymentService.processPayment(request);
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.PAYMENT_REQUIRED).body(e.getMessage());
        }
    }
}
