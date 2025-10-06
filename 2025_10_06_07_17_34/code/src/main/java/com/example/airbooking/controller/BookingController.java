package com.example.airbooking.controller;

import com.example.airbooking.model.Booking;
import com.example.airbooking.model.Passenger;
import com.example.airbooking.model.Payment;
import com.example.airbooking.service.BookingService;
import com.example.airbooking.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * REST controller for booking operations.
 */
@RestController
@RequestMapping("/api/bookings")
public class BookingController {
    @Autowired
    private BookingService bookingService;
    @Autowired
    private PaymentService paymentService;

    /**
     * Book a flight.
     * @param bookingRequest Request body containing flightId, passengerDetails, paymentInfo
     * @return Booking confirmation
     */
    @PostMapping
    public Booking bookFlight(@RequestBody Map<String, Object> bookingRequest) {
        String flightId = (String) bookingRequest.get("flightId");
        List<Passenger> passengerDetails = (List<Passenger>) bookingRequest.get("passengerDetails");
        Payment paymentInfo = (Payment) bookingRequest.get("paymentInfo");
        return bookingService.bookFlight(flightId, passengerDetails, paymentInfo);
    }

    /**
     * Get booking details by booking ID.
     * @param bookingId Booking ID
     * @return Booking
     */
    @GetMapping("/{bookingId}")
    public Booking getBooking(@PathVariable String bookingId) {
        return bookingService.getBooking(bookingId);
    }

    /**
     * Process payment for a booking.
     * @param paymentRequest Request body containing bookingId and paymentInfo
     * @return Payment status
     */
    @PostMapping("/payment/process")
    public Payment processPayment(@RequestBody Map<String, Object> paymentRequest) {
        String bookingId = (String) paymentRequest.get("bookingId");
        Payment paymentInfo = (Payment) paymentRequest.get("paymentInfo");
        return paymentService.processPayment(bookingId, paymentInfo);
    }
}