package com.example.airbooking.service;

import com.example.airbooking.model.Booking;
import com.example.airbooking.model.Flight;
import com.example.airbooking.model.User;
import com.example.airbooking.repository.BookingRepository;
import com.example.airbooking.repository.FlightRepository;
import com.example.airbooking.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

/**
 * Service for managing flight bookings.
 */
@Service
public class BookingService {
    private final BookingRepository bookingRepository;
    private final FlightRepository flightRepository;
    private final UserRepository userRepository;
    private final PaymentService paymentService;
    private final AirlineApiClient airlineApiClient;

    @Autowired
    public BookingService(BookingRepository bookingRepository, FlightRepository flightRepository, UserRepository userRepository, PaymentService paymentService, AirlineApiClient airlineApiClient) {
        this.bookingRepository = bookingRepository;
        this.flightRepository = flightRepository;
        this.userRepository = userRepository;
        this.paymentService = paymentService;
        this.airlineApiClient = airlineApiClient;
    }

    /**
     * Books a flight for a user after payment authorization and seat reservation.
     * @param flightId Flight ID
     * @param userId User ID
     * @param paymentInfo Payment information
     * @return Booking confirmation number
     */
    @Transactional
    public String bookFlight(Long flightId, Long userId, Map<String, Object> paymentInfo) {
        Optional<Flight> flightOpt = flightRepository.findById(flightId);
        Optional<User> userOpt = userRepository.findById(userId);
        if (!flightOpt.isPresent()) {
            throw new IllegalArgumentException("Flight unavailable or already booked");
        }
        if (!userOpt.isPresent()) {
            throw new IllegalArgumentException("User not found");
        }
        // Authorize payment
        boolean paymentOk = paymentService.authorize(paymentInfo);
        if (!paymentOk) {
            throw new RuntimeException("Payment authorization failed");
        }
        // Reserve seat with airline
        boolean reserved = airlineApiClient.reserveSeat(flightId);
        if (!reserved) {
            throw new RuntimeException("Flight unavailable or already booked");
        }
        // Create booking
        Booking booking = new Booking();
        booking.setFlight(flightOpt.get());
        booking.setUser(userOpt.get());
        booking.setConfirmationNumber(UUID.randomUUID().toString().replace("-", "").substring(0, 8).toUpperCase());
        booking.setStatus("CONFIRMED");
        booking.setBookedAt(LocalDateTime.now());
        bookingRepository.save(booking);
        // Optionally update user profile with booking (not shown)
        return booking.getConfirmationNumber();
    }

    /**
     * Retrieves booking details by booking ID.
     * @param bookingId Booking ID
     * @return Booking
     */
    public Booking getBooking(Long bookingId) {
        return bookingRepository.findById(bookingId)
                .orElseThrow(() -> new IllegalArgumentException("Booking ID not found"));
    }
}
