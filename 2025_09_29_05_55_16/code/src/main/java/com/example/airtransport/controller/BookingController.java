package com.example.airtransport.controller;

import com.example.airtransport.model.*;
import com.example.airtransport.service.*;
import com.example.airtransport.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.List;

/**
 * Controller for handling flight booking operations.
 * Exposes REST endpoints for searching flights, booking, and confirmation.
 */
@RestController
@RequestMapping("/api/flights")
public class BookingController {

    @Autowired
    private FlightSearchService flightSearchService;

    @Autowired
    private PaymentService paymentService;

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private NotificationService notificationService;

    /**
     * Search for available flights based on destination, date, and number of passengers.
     * @param destination The airport code of the destination.
     * @param date The date of travel (yyyy-MM-dd).
     * @param passengers Number of passengers.
     * @return List of available flights.
     */
    @GetMapping("/search")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<?> searchFlights(
            @RequestParam String destination,
            @RequestParam String date,
            @RequestParam int passengers) {
        // Input validation
        if (destination == null || destination.isEmpty() || !AirportCodeUtil.isValid(destination)) {
            return ResponseEntity.badRequest().body(new ErrorResponse("Invalid destination"));
        }
        if (!DateUtil.isValidFutureDate(date)) {
            return ResponseEntity.badRequest().body(new ErrorResponse("Invalid date"));
        }
        try {
            List<Flight> flights = flightSearchService.searchFlights(destination, date, passengers);
            return ResponseEntity.ok(flights);
        } catch (ExternalAPIException e) {
            return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body(new ErrorResponse(e.getMessage()));
        }
    }

    /**
     * Book a flight for a user.
     * @param bookingRequest Booking request payload.
     * @return Booking confirmation or error.
     */
    @PostMapping("/book")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<?> bookFlight(@RequestBody BookingRequest bookingRequest) {
        // Validate input
        if (bookingRequest.getFlightId() == null || bookingRequest.getFlightId().isEmpty()) {
            return ResponseEntity.badRequest().body(new ErrorResponse("Flight ID is required"));
        }
        if (bookingRequest.getUserId() == null || bookingRequest.getUserId().isEmpty()) {
            return ResponseEntity.badRequest().body(new ErrorResponse("User ID is required"));
        }
        try {
            // Check seat availability
            if (!flightSearchService.isSeatAvailable(bookingRequest.getFlightId())) {
                return ResponseEntity.status(HttpStatus.CONFLICT).body(new ErrorResponse("Seat not available"));
            }
            // Process payment
            PaymentResult paymentResult = paymentService.processPayment(bookingRequest.getPaymentInfo());
            if (!paymentResult.isSuccess()) {
                return ResponseEntity.status(HttpStatus.PAYMENT_REQUIRED).body(new ErrorResponse("Payment failed"));
            }
            // Save booking
            Booking booking = bookingRepository.saveBooking(bookingRequest, paymentResult);
            // Send confirmation
            notificationService.sendConfirmation(booking);
            return ResponseEntity.ok(new BookingResponse(booking.getBookingId(), "CONFIRMED", booking));
        } catch (PaymentProcessingException e) {
            return ResponseEntity.status(HttpStatus.PAYMENT_REQUIRED).body(new ErrorResponse(e.getMessage()));
        } catch (SeatUnavailableException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(new ErrorResponse(e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ErrorResponse("Booking failed: " + e.getMessage()));
        }
    }

    /**
     * Confirm a booking by booking ID.
     * @param id Booking ID.
     * @return Booking confirmation details.
     */
    @GetMapping("/confirm/{id}")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<?> confirmBooking(@PathVariable String id) {
        Booking booking = bookingRepository.findByBookingId(id);
        if (booking == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorResponse("Booking not found"));
        }
        return ResponseEntity.ok(new BookingResponse(booking.getBookingId(), booking.getStatus(), booking));
    }
}
