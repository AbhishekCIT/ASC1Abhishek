package com.example.airbooking.controller;

import com.example.airbooking.model.*;
import com.example.airbooking.service.*;
import com.example.airbooking.util.ValidationUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

/**
 * Controller for handling flight search, booking, and payment APIs.
 */
@RestController
@RequestMapping("/api")
public class BookingController {

    @Autowired
    private FlightService flightService;

    @Autowired
    private PaymentService paymentService;

    @Autowired
    private NotificationService notificationService;

    @Autowired
    private BookingRepository bookingRepository;

    /**
     * Search available flights by date, destination, and class.
     */
    @GetMapping("/flights/search")
    public ResponseEntity<List<FlightResponse>> searchFlights(@RequestParam String date,
                                                             @RequestParam String destination,
                                                             @RequestParam String clazz) {
        // Validate input
        ValidationUtil.validateDate(date);
        ValidationUtil.validateDestination(destination);
        List<FlightResponse> flights = flightService.searchFlights(date, destination, clazz);
        return ResponseEntity.ok(flights);
    }

    /**
     * Get details for a specific flight.
     */
    @GetMapping("/flights/{flightId}")
    public ResponseEntity<FlightDetailResponse> getFlightDetails(@PathVariable Long flightId) {
        FlightDetailResponse details = flightService.getFlightDetails(flightId);
        return ResponseEntity.ok(details);
    }

    /**
     * Book a ticket for a flight.
     */
    @PostMapping("/bookings")
    public ResponseEntity<BookingResponse> bookTicket(@RequestBody BookingRequest bookingRequest) {
        // Validate passenger details
        ValidationUtil.validatePassengerDetails(bookingRequest.getPassengerDetails());
        // Check flight availability
        if (!flightService.isFlightAvailable(bookingRequest.getFlightId())) {
            throw new BookingException("Flight not available");
        }
        // Process payment
        PaymentResponse paymentResponse = paymentService.processPayment(bookingRequest.getPaymentInfo());
        if (!"SUCCESS".equals(paymentResponse.getStatus())) {
            throw new BookingException("Payment failed");
        }
        // Save booking
        Booking booking = bookingRepository.saveBooking(bookingRequest, paymentResponse);
        // Send confirmation
        notificationService.sendConfirmation(booking);
        BookingResponse response = new BookingResponse(booking.getBookingId(), "CONFIRMED", "sent");
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    /**
     * Process a payment for a booking.
     */
    @PostMapping("/payments")
    public ResponseEntity<PaymentResponse> makePayment(@RequestBody PaymentRequest paymentRequest) {
        PaymentResponse response = paymentService.processPayment(paymentRequest);
        if (!"SUCCESS".equals(response.getStatus())) {
            throw new PaymentException("Payment gateway error");
        }
        return ResponseEntity.ok(response);
    }
}
