package com.example.airtransport.controller;

import com.example.airtransport.model.BookingRequest;
import com.example.airtransport.model.BookingResponse;
import com.example.airtransport.service.BookingService;
import com.example.airtransport.util.ValidationUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * REST controller for handling booking requests.
 */
@RestController
@RequestMapping("/api/bookings")
public class BookingController {
    private static final Logger logger = LoggerFactory.getLogger(BookingController.class);

    @Autowired
    private BookingService bookingService;
    @Autowired
    private ValidationUtil validationUtil;

    /**
     * Exposes POST API endpoint for booking a flight.
     * @param request Booking request
     * @return Booking confirmation or error
     */
    @PostMapping
    public ResponseEntity<?> createBooking(@Valid @RequestBody BookingRequest request) {
        logger.info("Received booking request: {}", request);
        try {
            validationUtil.validateBookingRequest(request);
            BookingResponse response = bookingService.bookFlight(request);
            return ResponseEntity.ok(response);
        } catch (IllegalArgumentException e) {
            logger.error("Invalid booking request: {}", e.getMessage());
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (com.example.airtransport.exception.PaymentFailedException e) {
            logger.error("Payment failed: {}", e.getMessage());
            return ResponseEntity.status(402).body(e.getMessage());
        } catch (com.example.airtransport.exception.SeatUnavailableException e) {
            logger.error("Seat unavailable: {}", e.getMessage());
            return ResponseEntity.status(409).body(e.getMessage());
        } catch (Exception e) {
            logger.error("Internal server error: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Internal server error.");
        }
    }
}
