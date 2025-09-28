package com.airtransport.booking.controller;

import com.airtransport.booking.entity.Booking;
import com.airtransport.booking.entity.PaymentInfo;
import com.airtransport.booking.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * Controller for booking APIs.
 */
@RestController
@RequestMapping("/api/bookings")
public class BookingController {
    @Autowired
    private BookingService bookingService;

    /**
     * Book a flight.
     * @param request Booking request JSON
     * @return Booking confirmation
     */
    @PostMapping
    public ResponseEntity<Map<String, Object>> bookFlight(@RequestBody Map<String, Object> request) {
        Long flightId = Long.valueOf(request.get("flightId").toString());
        Long userId = Long.valueOf(request.get("userId").toString());
        Object passengerInfo = request.get("passengerInfo");
        Map<String, Object> paymentMap = (Map<String, Object>) request.get("paymentInfo");
        PaymentInfo paymentInfo = new PaymentInfo();
        paymentInfo.setPaymentMethod(paymentMap.get("paymentMethod").toString());
        // Card details would be handled securely in a real implementation
        Booking booking = bookingService.createBooking(flightId, userId, passengerInfo, paymentInfo);
        Map<String, Object> response = new HashMap<>();
        response.put("bookingRef", booking.getBookingRef());
        response.put("status", booking.getStatus());
        return ResponseEntity.ok(response);
    }

    /**
     * Get booking details by reference.
     * @param bookingRef Booking reference
     * @return Booking details
     */
    @GetMapping("/{bookingRef}")
    public ResponseEntity<Booking> getBooking(@PathVariable String bookingRef) {
        Booking booking = bookingService.getBooking(bookingRef);
        return ResponseEntity.ok(booking);
    }
}
