package com.example.airbooking.controller;

import com.example.airbooking.model.Booking;
import com.example.airbooking.model.PaymentCallbackRequest;
import com.example.airbooking.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * REST Controller for booking flights and handling payment callbacks
 */
@RestController
@RequestMapping("/api/bookings")
public class BookingController {

    @Autowired
    private BookingService bookingService;

    /**
     * Book a flight with seat selection and payment info
     * @param booking booking request
     * @return booking confirmation
     */
    @PostMapping
    public ResponseEntity<Booking> bookFlight(@RequestBody Booking booking) {
        Booking confirmedBooking = bookingService.bookFlight(booking);
        return ResponseEntity.ok(confirmedBooking);
    }
}

@RestController
@RequestMapping("/api/payments")
class PaymentController {
    @Autowired
    private BookingService bookingService;

    /**
     * Payment gateway callback endpoint
     * @param request payment callback request
     * @return payment status
     */
    @PostMapping("/callback")
    public ResponseEntity<String> paymentCallback(@RequestBody PaymentCallbackRequest request) {
        String status = bookingService.handlePaymentCallback(request);
        return ResponseEntity.ok(status);
    }
}
