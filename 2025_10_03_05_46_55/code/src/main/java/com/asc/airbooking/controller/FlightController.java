package com.asc.airbooking.controller;

import com.asc.airbooking.model.*;
import com.asc.airbooking.service.FlightService;
import com.asc.airbooking.service.PaymentService;
import com.asc.airbooking.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import jakarta.validation.Valid;

/**
 * Controller for flight search, booking, and payment APIs.
 */
@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class FlightController {
    private final FlightService flightService;
    private final PaymentService paymentService;
    private final AuthService authService;
    private static final Logger logger = LoggerFactory.getLogger(FlightController.class);

    /**
     * Searches for available flights.
     * @param request FlightSearchRequest
     * @param authentication OAuth2 authentication
     * @return FlightSearchResponse
     */
    @GetMapping("/flights/search")
    public ResponseEntity<FlightSearchResponse> searchFlights(@Valid @RequestBody FlightSearchRequest request, Authentication authentication) {
        if (!authService.authenticate(authentication)) {
            logger.warn("Unauthorized flight search attempt.");
            return ResponseEntity.status(401).build();
        }
        FlightSearchResponse response = flightService.findFlights(request);
        return ResponseEntity.ok(response);
    }

    /**
     * Books a flight for a passenger.
     * @param request BookFlightRequest
     * @param authentication OAuth2 authentication
     * @return BookFlightResponse
     */
    @PostMapping("/flights/book")
    public ResponseEntity<BookFlightResponse> bookFlight(@Valid @RequestBody BookFlightRequest request, Authentication authentication) {
        if (!authService.authenticate(authentication)) {
            logger.warn("Unauthorized flight booking attempt.");
            return ResponseEntity.status(401).build();
        }
        BookFlightResponse response = flightService.bookFlight(request);
        return ResponseEntity.ok(response);
    }

    /**
     * Processes payment for a booking.
     * @param request PaymentRequest
     * @param authentication OAuth2 authentication
     * @return PaymentResponse
     */
    @PostMapping("/payments/process")
    public ResponseEntity<PaymentResponse> processPayment(@Valid @RequestBody PaymentRequest request, Authentication authentication) {
        if (!authService.authenticate(authentication)) {
            logger.warn("Unauthorized payment processing attempt.");
            return ResponseEntity.status(401).build();
        }
        boolean success = paymentService.processPayment(request.getAmount(), request.getToken());
        PaymentResponse response = new PaymentResponse();
        response.setPaymentStatus(success ? "success" : "failed");
        response.setTransactionId(success ? "txn_" + System.currentTimeMillis() : null);
        return ResponseEntity.ok(response);
    }
}
