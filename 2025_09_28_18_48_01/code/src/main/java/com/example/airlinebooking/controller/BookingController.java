package com.example.airlinebooking.controller;

import com.example.airlinebooking.model.BookingRequest;
import com.example.airlinebooking.model.BookingResponse;
import com.example.airlinebooking.model.PaymentCallbackRequest;
import com.example.airlinebooking.model.PaymentCallbackResponse;
import com.example.airlinebooking.service.BookingService;
import com.example.airlinebooking.service.PaymentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * REST controller for booking and payment APIs.
 */
@RestController
@RequestMapping("/api")
public class BookingController {
    @Autowired
    private BookingService bookingService;
    @Autowired
    private PaymentService paymentService;

    /**
     * Books a flight with passenger and payment details.
     * @param request booking request
     * @return booking confirmation
     */
    @PostMapping("/bookings")
    public ResponseEntity<BookingResponse> bookFlight(@Valid @RequestBody BookingRequest request) {
        BookingResponse response = bookingService.createBooking(request);
        return ResponseEntity.ok(response);
    }

    /**
     * Handles payment gateway callback.
     * @param request payment callback request
     * @return payment callback response
     */
    @PostMapping("/payments/callback")
    public ResponseEntity<PaymentCallbackResponse> paymentCallback(@RequestBody PaymentCallbackRequest request) {
        PaymentCallbackResponse response = paymentService.handlePaymentCallback(request);
        return ResponseEntity.ok(response);
    }
}
