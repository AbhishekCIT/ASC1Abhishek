package com.example.airlinebooking.service;

import com.example.airlinebooking.entity.*;
import com.example.airlinebooking.model.*;
import com.example.airlinebooking.repository.*;
import com.example.airlinebooking.util.AirlineAPIClient;
import com.example.airlinebooking.util.NotificationService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Service for business logic related to flight operations.
 */
@Service
@RequiredArgsConstructor
public class FlightService {
    private final AirlineAPIClient airlineAPIClient;
    private final BookingRepository bookingRepository;
    private final FlightRepository flightRepository;
    private final SeatRepository seatRepository;
    private final UserRepository userRepository;
    private final NotificationService notificationService;
    private final PaymentService paymentService;

    /**
     * Search for available flights (cached for 2 seconds).
     */
    @Cacheable(value = "flightSearch", key = "#request.origin + #request.destination + #request.date", unless = "#result == null", cacheManager = "cacheManager")
    public List<FlightSearchResponse> searchFlights(FlightSearchRequest request) {
        if (request.getOrigin() == null || request.getOrigin().isBlank()) {
            throw new IllegalArgumentException("Origin cannot be empty");
        }
        if (request.getDestination() == null || request.getDestination().isBlank()) {
            throw new IllegalArgumentException("Destination cannot be empty");
        }
        if (request.getDate() == null || !request.getDate().isAfter(LocalDate.now())) {
            throw new IllegalArgumentException("Date must be in the future");
        }
        // Integrate with airline APIs
        List<Map<String, Object>> flights = airlineAPIClient.fetchAvailableFlights(request.getOrigin(), request.getDestination(), java.sql.Date.valueOf(request.getDate()));
        return flights.stream().map(f -> FlightSearchResponse.builder()
                .flightId((String) f.get("flightId"))
                .fare((Double) f.get("fare"))
                .seats((List<String>) f.get("seats"))
                .build()).collect(Collectors.toList());
    }

    /**
     * View flight details by flightId.
     */
    public Map<String, Object> viewFlight(String flightId) {
        return airlineAPIClient.fetchFlightDetails(flightId);
    }

    /**
     * Book a ticket for a user.
     */
    @Transactional
    public BookTicketResponse bookTicket(BookTicketRequest request) {
        // Validate user
        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new IllegalArgumentException("User not found"));
        // Validate flight
        Flight flight = flightRepository.findById(request.getFlightId())
                .orElseThrow(() -> new IllegalArgumentException("Flight not found"));
        // Validate seat
        Seat seat = seatRepository.findByFlightAndSeatNumber(flight, request.getSeat());
        if (seat == null || !seat.getIsAvailable()) {
            throw new IllegalArgumentException("Seat not available");
        }
        // Reserve seat
        seat.setIsAvailable(false);
        seatRepository.save(seat);
        // Create booking
        String bookingId = UUID.randomUUID().toString();
        Booking booking = Booking.builder()
                .bookingId(bookingId)
                .user(user)
                .flight(flight)
                .seat(seat)
                .status("CONFIRMED")
                .createdAt(LocalDateTime.now())
                .build();
        bookingRepository.save(booking);
        // Process payment
        PaymentRequest paymentRequest = new PaymentRequest();
        paymentRequest.setBookingId(bookingId);
        paymentRequest.setPaymentDetails(request.getPayment());
        PaymentResponse paymentResponse = paymentService.processPayment(paymentRequest);
        // Send notification
        notificationService.sendConfirmation(user.getUserId(), "Booking confirmed: " + bookingId);
        Map<String, Object> confirmation = new HashMap<>();
        confirmation.put("paymentStatus", paymentResponse.getStatus());
        confirmation.put("flightId", flight.getFlightId());
        confirmation.put("seat", seat.getSeatNumber());
        return BookTicketResponse.builder()
                .bookingId(bookingId)
                .status("CONFIRMED")
                .confirmation(confirmation)
                .build();
    }

    /**
     * Get booking confirmation details.
     */
    public BookingConfirmationResponse getBookingConfirmation(String bookingId) {
        Booking booking = bookingRepository.findById(bookingId)
                .orElseThrow(() -> new IllegalArgumentException("Booking not found"));
        Map<String, Object> confirmation = new HashMap<>();
        confirmation.put("flightId", booking.getFlight().getFlightId());
        confirmation.put("seat", booking.getSeat().getSeatNumber());
        confirmation.put("status", booking.getStatus());
        return BookingConfirmationResponse.builder()
                .bookingId(bookingId)
                .confirmation(confirmation)
                .build();
    }
}
