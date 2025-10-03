package com.airtransport.controller;

import com.airtransport.model.Flight;
import com.airtransport.model.BookingRequest;
import com.airtransport.model.BookingResponse;
import com.airtransport.model.PaymentRequest;
import com.airtransport.model.PaymentResponse;
import com.airtransport.model.EmailRequest;
import com.airtransport.model.EmailResponse;
import com.airtransport.service.FlightSearchService;
import com.airtransport.service.BookingService;
import com.airtransport.service.PaymentGatewayService;
import com.airtransport.service.EmailNotificationService;
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
    private EmailNotificationService emailNotificationService;

    /**
     * Search for flights by origin, destination, and date.
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
    @PostMapping("/flights/book")
    public ResponseEntity<?> bookFlight(@RequestBody BookingRequest bookingRequest) {
        try {
            BookingResponse response = bookingService.bookFlight(
                bookingRequest.getFlightId(),
                bookingRequest.getUserId(),
                bookingRequest.getPassengerDetails(),
                bookingRequest.getPaymentInfo()
            );
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("{\"error\": \"" + e.getMessage() + "\"}");
        }
    }

    /**
     * Process payment for a booking.
     */
    @PostMapping("/payments/process")
    public ResponseEntity<?> processPayment(@RequestBody PaymentRequest paymentRequest) {
        try {
            PaymentResponse response = paymentGatewayService.processPayment(
                paymentRequest.getBookingId(),
                paymentRequest.getPaymentInfo()
            );
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("{\"error\": \"" + e.getMessage() + "\"}");
        }
    }

    /**
     * Send booking confirmation email.
     */
    @PostMapping("/notifications/email")
    public ResponseEntity<?> sendBookingConfirmation(@RequestBody EmailRequest emailRequest) {
        try {
            EmailResponse response = emailNotificationService.sendBookingConfirmation(
                emailRequest.getBookingId(),
                emailRequest.getEmail()
            );
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("{\"error\": \"" + e.getMessage() + "\"}");
        }
    }
}
