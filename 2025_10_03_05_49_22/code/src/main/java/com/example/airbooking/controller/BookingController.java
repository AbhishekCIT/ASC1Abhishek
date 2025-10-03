package com.example.airbooking.controller;

import com.example.airbooking.model.Booking;
import com.example.airbooking.model.Passenger;
import com.example.airbooking.model.PaymentInfo;
import com.example.airbooking.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * Controller for booking APIs.
 */
@RestController
@RequestMapping("/api/bookings")
public class BookingController {
    private final BookingService bookingService;

    @Autowired
    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    /**
     * Book a ticket for a flight.
     * Example: POST /api/bookings
     * Request: {"flightId":123,"passengerInfo":{...},"seat":"12A","baggage":1,"payment":{...}}
     */
    @PostMapping
    public ResponseEntity<?> bookTicket(@RequestBody Map<String, Object> request) {
        try {
            Long flightId = Long.valueOf(request.get("flightId").toString());
            Map<String, Object> passengerMap = (Map<String, Object>) request.get("passengerInfo");
            Passenger passenger = new Passenger();
            passenger.setName(passengerMap.get("name").toString());
            passenger.setContact(passengerMap.get("contact").toString());
            passenger.setPassport(passengerMap.get("passport").toString());
            String seatNumber = request.get("seat").toString();
            int baggage = Integer.parseInt(request.get("baggage").toString());
            Map<String, Object> paymentMap = (Map<String, Object>) request.get("payment");
            PaymentInfo paymentInfo = new PaymentInfo();
            paymentInfo.setCardNumber(paymentMap.get("cardNumber").toString());
            paymentInfo.setExpiry(paymentMap.get("expiry").toString());
            paymentInfo.setCvv(paymentMap.get("cvv").toString());
            paymentInfo.setCardHolderName(paymentMap.get("cardHolderName").toString());
            Booking booking = bookingService.bookTicket(flightId, passenger, seatNumber, baggage, paymentInfo);
            return ResponseEntity.ok(Map.of(
                    "bookingId", booking.getId(),
                    "status", booking.getStatus(),
                    "confirmationCode", booking.getConfirmationCode()
            ));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        }
    }
}
