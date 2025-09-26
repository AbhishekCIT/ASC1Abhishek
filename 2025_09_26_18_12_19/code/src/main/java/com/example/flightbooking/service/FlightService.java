package com.example.flightbooking.service;

import com.example.flightbooking.model.*;
import com.example.flightbooking.repository.*;
import com.example.flightbooking.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDate;
import java.util.List;

/**
 * Service layer for flight search and booking logic.
 */
@Service
public class FlightService {
    @Autowired
    private FlightRepository flightRepository;
    @Autowired
    private BookingService bookingService;
    @Autowired
    private PaymentService paymentService;
    @Autowired
    private EmailService emailService;
    @Autowired
    private FlightAPIClient flightAPIClient;

    /**
     * Search for available flights based on origin, destination, and date.
     * @param origin IATA code
     * @param destination IATA code
     * @param date travel date (yyyy-MM-dd)
     * @return FlightSearchResponse containing list of flights
     */
    public FlightSearchResponse searchFlights(String origin, String destination, String date) {
        // Validate input
        if (!AirportCodeUtil.isValidIATACode(origin)) {
            throw new InvalidAirportCodeException("Invalid airport code.");
        }
        if (!AirportCodeUtil.isValidIATACode(destination)) {
            throw new InvalidAirportCodeException("Invalid airport code.");
        }
        LocalDate travelDate = LocalDate.parse(date);
        if (travelDate.isBefore(LocalDate.now())) {
            throw new DateInPastException("Travel date cannot be in the past.");
        }
        // Fetch from third-party API
        List<Flight> flights = flightAPIClient.getAvailableFlights(origin, destination, travelDate);
        return new FlightSearchResponse(flights);
    }

    /**
     * Book a flight ticket with payment processing and confirmation email.
     * @param request BookingRequest
     * @return BookingResponse
     */
    @Transactional
    public BookingResponse bookFlight(BookingRequest request) {
        // Validate flight availability
        Flight flight = flightRepository.findById(request.getFlightId())
                .orElseThrow(() -> new FlightNotAvailableException("Flight not available."));
        if (flight.getAvailability() <= 0) {
            throw new FlightNotAvailableException("Flight not available.");
        }
        // Process payment
        PaymentResult paymentResult = paymentService.processPayment(request.getPaymentDetails());
        if (!paymentResult.isSuccess()) {
            throw new PaymentFailedException("Payment processing failed.");
        }
        // Create booking
        Booking booking = bookingService.createBooking(request, paymentResult);
        // Send confirmation email
        emailService.sendConfirmationEmail(booking);
        // Build response
        return new BookingResponse(booking.getBookingId(), "CONFIRMED", "sent");
    }
}
