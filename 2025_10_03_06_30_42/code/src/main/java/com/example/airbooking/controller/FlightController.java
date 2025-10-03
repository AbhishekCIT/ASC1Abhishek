package com.example.airbooking.controller;

import com.example.airbooking.entity.Flight;
import com.example.airbooking.entity.Booking;
import com.example.airbooking.entity.Passenger;
import com.example.airbooking.entity.Payment;
import com.example.airbooking.service.FlightSearchService;
import com.example.airbooking.service.BookingService;
import com.example.airbooking.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

/**
 * Controller for flight search, booking, and payment APIs.
 */
@RestController
@RequestMapping("/api")
public class FlightController {
    @Autowired
    private FlightSearchService flightSearchService;
    @Autowired
    private BookingService bookingService;
    @Autowired
    private PaymentService paymentService;

    /**
     * Search flights API
     */
    @GetMapping("/flights/search")
    public ResponseEntity<?> searchFlights(@RequestParam String origin,
                                           @RequestParam String destination,
                                           @RequestParam String date) {
        LocalDate travelDate = LocalDate.parse(date);
        List<Flight> flights = flightSearchService.searchFlights(origin, destination, travelDate);
        return ResponseEntity.ok(Map.of("flights", flights));
    }

    /**
     * View flight details API
     */
    @GetMapping("/flights/{flightId}")
    public ResponseEntity<?> getFlightDetails(@PathVariable String flightId) {
        Flight flight = flightSearchService.getFlightDetails(flightId);
        if (flight == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(flight);
    }

    /**
     * Book ticket API
     */
    @PostMapping("/bookings")
    public ResponseEntity<?> bookTicket(@RequestBody Map<String, Object> request) {
        String flightId = (String) request.get("flightId");
        String email = (String) request.get("email");
        List<Map<String, String>> passengerDetailsList = (List<Map<String, String>>) request.get("passengerDetails");
        Map<String, Object> paymentInfoMap = (Map<String, Object>) request.get("paymentInfo");

        // Convert passenger details
        List<Passenger> passengers = new java.util.ArrayList<>();
        for (Map<String, String> pd : passengerDetailsList) {
            Passenger passenger = new Passenger();
            passenger.setName(pd.get("name"));
            passenger.setPassportNumber(pd.get("passportNumber"));
            passenger.setNationality(pd.get("nationality"));
            passengers.add(passenger);
        }
        // Convert payment info
        Payment payment = new Payment();
        payment.setAmount(new java.math.BigDecimal(paymentInfoMap.get("amount").toString()));

        Booking booking = bookingService.bookFlight(flightId, passengers, payment, email);
        return ResponseEntity.ok(Map.of(
            "bookingId", booking.getBookingId(),
            "status", booking.getStatus(),
            "email", booking.getEmail()
        ));
    }

    /**
     * Payment processing API
     */
    @PostMapping("/payments")
    public ResponseEntity<?> processPayment(@RequestBody Map<String, Object> request) {
        String bookingId = (String) request.get("bookingId");
        Map<String, Object> paymentInfoMap = (Map<String, Object>) request.get("paymentInfo");
        Payment payment = new Payment();
        payment.setAmount(new java.math.BigDecimal(paymentInfoMap.get("amount").toString()));
        payment.setStatus("SUCCESS");
        Payment result = paymentService.processPayment(payment);
        return ResponseEntity.ok(Map.of(
            "paymentId", result.getPaymentId(),
            "status", result.getStatus()
        ));
    }
}
