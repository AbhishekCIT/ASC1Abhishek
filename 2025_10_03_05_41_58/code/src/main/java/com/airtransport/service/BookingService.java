package com.airtransport.service;

import com.airtransport.entity.*;
import com.airtransport.model.BookingRequest;
import com.airtransport.model.BookingResponse;
import com.airtransport.repository.*;
import com.airtransport.util.LoggingUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * Service for managing the booking workflow.
 */
@Service
@RequiredArgsConstructor
public class BookingService {
    private final BookingRepository bookingRepository;
    private final UserRepository userRepository;
    private final FlightRepository flightRepository;
    private final SeatRepository seatRepository;
    private final PaymentService paymentService;
    private final FlightSearchService flightSearchService;
    private final NotificationService notificationService;
    private final LoggingUtil loggingUtil;

    /**
     * Book a flight for a user.
     * @param request Booking request
     * @return BookingResponse
     */
    @Transactional
    public BookingResponse bookFlight(BookingRequest request) {
        // Validate passenger details
        if (request.getPassengerDetails() == null ||
            request.getPassengerDetails().getName() == null ||
            request.getPassengerDetails().getAge() == null) {
            throw new IllegalArgumentException("Passenger details incomplete");
        }
        // Validate flight availability
        if (!flightSearchService.checkAvailability(request.getFlightId())) {
            throw new RuntimeException("Selected flight is no longer available");
        }
        // Fetch flight
        Flight flight = flightRepository.findById(request.getFlightId())
                .orElseThrow(() -> new RuntimeException("Flight not found"));
        // For demo, assume user is always present (in real app, get from security context)
        User user = userRepository.findAll().stream().findFirst().orElseThrow(() -> new RuntimeException("User not found"));
        // Create booking
        Booking booking = Booking.builder()
                .bookingId(UUID.randomUUID().toString())
                .user(user)
                .flight(flight)
                .bookingDate(LocalDateTime.now())
                .status("PENDING_PAYMENT")
                .build();
        bookingRepository.save(booking);
        // Process payment
        paymentService.processPayment(booking.getBookingId(), flight.getPrice(), request.getPaymentInfo().getMethod());
        // Update booking status
        booking.setStatus("CONFIRMED");
        bookingRepository.save(booking);
        // Update seat inventory (for demo, not implemented)
        // Send notification
        notificationService.sendBookingConfirmation(booking.getBookingId(), user.getEmail());
        // Build response
        BookingResponse.TicketDetails ticketDetails = new BookingResponse.TicketDetails(
                flight.getFlightId(),
                request.getPassengerDetails().getName(),
                "A1", // seat number (mocked)
                flight.getDeparture().toString(),
                flight.getArrival().toString()
        );
        return new BookingResponse(booking.getBookingId(), booking.getStatus(), ticketDetails);
    }
}
