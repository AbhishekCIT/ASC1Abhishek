package com.example.airline.controller;

import com.example.airline.dto.BookingRequest;
import com.example.airline.dto.BookingResponse;
import com.example.airline.dto.PaymentRequest;
import com.example.airline.dto.PaymentResponse;
import com.example.airline.service.BookingService;
import com.example.airline.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/flights")
public class BookingController {
    @Autowired
    private BookingService bookingService;
    @Autowired
    private PaymentService paymentService;

    /**
     * Book a flight and process payment.
     * @param request BookingRequest
     * @return BookingResponse
     */
    @PostMapping("/book")
    public ResponseEntity<BookingResponse> bookFlight(@RequestBody BookingRequest request) {
        BookingResponse response = bookingService.bookFlight(request);
        return ResponseEntity.ok(response);
    }

    /**
     * Process payment for a booking.
     * @param request PaymentRequest
     * @return PaymentResponse
     */
    @PostMapping("/payments/process")
    public ResponseEntity<PaymentResponse> processPayment(@RequestBody PaymentRequest request) {
        PaymentResponse response = paymentService.processPayment(request);
        return ResponseEntity.ok(response);
    }
}