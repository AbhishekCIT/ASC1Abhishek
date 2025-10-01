package com.example.airlinebooking.controller;

import com.example.airlinebooking.model.Booking;
import com.example.airlinebooking.model.Flight;
import com.example.airlinebooking.model.Payment;
import com.example.airlinebooking.service.BookingService;
import com.example.airlinebooking.service.FlightSearchService;
import com.example.airlinebooking.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * REST controller for airline booking APIs.
 */
@RestController
@RequestMapping("/api")
public class BookingController {
    @Autowired
    private FlightSearchService flightSearchService;
    @Autowired
    private BookingService bookingService;
    @Autowired
    private PaymentService paymentService;

    /**
     * API to search for available flights.
     */
    @GetMapping("/flights/search")
    public ResponseEntity<?> searchFlights(@RequestParam String date,
                                           @RequestParam String destination,
                                           @RequestParam(name = "class") String flightClass) {
        try {
            List<Flight> flights = flightSearchService.searchFlights(date, destination, flightClass);
            Map<String, Object> response = new HashMap<>();
            response.put("flights", flights);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        }
    }

    /**
     * API to book a ticket for a flight.
     */
    @PostMapping("/bookings")
    public ResponseEntity<?> bookTicket(@RequestBody Map<String, Object> request) {
        try {
            String flightId = (String) request.get("flightId");
            String passengerId = (String) request.get("passengerId");
            String flightClass = (String) request.get("class");
            Object paymentInfo = request.get("paymentInfo");
            Booking booking = bookingService.bookTicket(flightId, passengerId, flightClass, paymentInfo);
            return ResponseEntity.ok(Map.of("bookingRef", booking.getBookingRef(), "status", booking.getStatus()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of("error", e.getMessage()));
        }
    }

    /**
     * API to charge payment (for demonstration, this is part of booking).
     */
    @PostMapping("/payments/charge")
    public ResponseEntity<?> chargePayment(@RequestBody Map<String, Object> request) {
        try {
            BigDecimal amount = new BigDecimal(request.get("amount").toString());
            Object paymentInfo = request.get("cardDetails");
            Payment payment = paymentService.charge(amount, paymentInfo);
            return ResponseEntity.ok(Map.of("paymentId", payment.getPaymentId(), "status", payment.getStatus()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of("error", e.getMessage()));
        }
    }

    /**
     * API to get booking confirmation details.
     */
    @GetMapping("/bookings/{bookingRef}/confirm")
    public ResponseEntity<?> getBookingConfirmation(@PathVariable String bookingRef) {
        try {
            Booking booking = bookingService.getBookingConfirmation(bookingRef);
            Map<String, Object> details = new HashMap<>();
            details.put("bookingRef", booking.getBookingRef());
            details.put("status", booking.getStatus());
            details.put("details", booking);
            return ResponseEntity.ok(details);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("error", e.getMessage()));
        }
    }
}
