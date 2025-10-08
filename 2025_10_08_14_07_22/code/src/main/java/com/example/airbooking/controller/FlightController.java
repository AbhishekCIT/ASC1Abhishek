package com.example.airbooking.controller;

import com.example.airbooking.model.*;
import com.example.airbooking.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

/**
 * Controller for flight search, booking, payment, and confirmation APIs.
 */
@RestController
@RequestMapping("/api")
public class FlightController {

    @Autowired
    private FlightSearchService flightSearchService;
    @Autowired
    private BookingService bookingService;
    @Autowired
    private PaymentGatewayService paymentGatewayService;
    @Autowired
    private ConfirmationService confirmationService;

    /**
     * Search for available flights by origin, destination, and date.
     * @param searchRequest the search request
     * @return list of available flights
     */
    @GetMapping("/flights/search")
    public ResponseEntity<FlightSearchResponse> searchFlights(@RequestBody FlightSearchRequest searchRequest) {
        return ResponseEntity.ok(flightSearchService.searchFlights(searchRequest));
    }

    /**
     * Book a flight for a user.
     * @param bookingRequest the booking request
     * @return booking confirmation
     */
    @PostMapping("/bookings")
    public ResponseEntity<BookingResponse> bookFlight(@RequestBody BookingRequest bookingRequest) {
        return ResponseEntity.ok(bookingService.createBooking(bookingRequest));
    }

    /**
     * Make payment for a booking.
     * @param paymentRequest the payment request
     * @return payment status
     */
    @PostMapping("/payments")
    public ResponseEntity<PaymentResponse> makePayment(@RequestBody PaymentRequest paymentRequest) {
        return ResponseEntity.ok(paymentGatewayService.processPayment(paymentRequest));
    }

    /**
     * Send booking confirmation to the user.
     * @param confirmationRequest the confirmation request
     * @return confirmation status
     */
    @PostMapping("/confirmations")
    public ResponseEntity<ConfirmationResponse> sendConfirmation(@RequestBody ConfirmationRequest confirmationRequest) {
        return ResponseEntity.ok(confirmationService.sendConfirmation(confirmationRequest));
    }
}
