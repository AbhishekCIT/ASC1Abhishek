package com.example.airtransport.controller;

import com.example.airtransport.model.BookingRequest;
import com.example.airtransport.model.BookingResponse;
import com.example.airtransport.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

/**
 * Controller for handling booking requests.
 */
@RestController
@RequestMapping("/api/bookings")
public class BookingController {

    @Autowired
    private BookingService bookingService;

    /**
     * Book a flight with passenger and payment information.
     * @param request BookingRequest containing flightId, passengerInfo, paymentInfo
     * @return BookingResponse with bookingRef and status
     */
    @PostMapping
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<?> bookFlight(@RequestBody BookingRequest request) {
        try {
            BookingResponse response = bookingService.bookFlight(request);
            return ResponseEntity.ok(response);
        } catch (Exception ex) {
            return ResponseEntity.badRequest().body("Error: " + ex.getMessage());
        }
    }
}
