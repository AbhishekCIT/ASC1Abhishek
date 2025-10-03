package com.example.airtransport.service;

import com.example.airtransport.entity.Booking;
import com.example.airtransport.entity.Flight;
import com.example.airtransport.entity.User;
import com.example.airtransport.model.BookingRequest;
import com.example.airtransport.model.BookingResponse;
import com.example.airtransport.model.PaymentInfo;
import com.example.airtransport.repository.BookingRepository;
import com.example.airtransport.repository.FlightRepository;
import com.example.airtransport.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

/**
 * Service for booking creation and confirmation logic.
 */
@Service
@RequiredArgsConstructor
public class BookingService {
    private final BookingRepository bookingRepository;
    private final FlightRepository flightRepository;
    private final UserRepository userRepository;
    private final PaymentService paymentService;
    private final EmailService emailService;

    /**
     * Creates a new booking, processes payment, and sends confirmation email.
     * @param request Booking request
     * @return BookingResponse
     */
    @Transactional
    public BookingResponse createBooking(BookingRequest request) {
        // Validate and fetch entities
        Flight flight = flightRepository.findById(request.getFlightId())
                .orElseThrow(() -> new RuntimeException("Flight not available"));
        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));
        // Create booking entity
        Booking booking = Booking.builder()
                .bookingId(UUID.randomUUID().toString())
                .user(user)
                .flight(flight)
                .bookingDate(LocalDateTime.now())
                .totalPrice(flight.getBaseFare())
                .status("PENDING")
                .build();
        bookingRepository.save(booking);
        // Process payment
        boolean paymentSuccess = paymentService.processPayment(booking.getBookingId(), request.getPaymentInfo());
        if (!paymentSuccess) {
            booking.setStatus("FAILED");
            bookingRepository.save(booking);
            throw new RuntimeException("Payment failed");
        }
        booking.setStatus("CONFIRMED");
        bookingRepository.save(booking);
        // Send confirmation email
        boolean emailSent = emailService.sendConfirmationEmail(user.getEmail(), booking);
        return BookingResponse.builder()
                .bookingId(booking.getBookingId())
                .status(booking.getStatus())
                .emailSent(emailSent)
                .build();
    }

    /**
     * Confirms a booking and returns its status.
     * @param bookingId Booking ID
     * @return BookingResponse
     */
    public BookingResponse confirmBooking(String bookingId) {
        Booking booking = bookingRepository.findById(bookingId)
                .orElseThrow(() -> new RuntimeException("Booking not found"));
        return BookingResponse.builder()
                .bookingId(booking.getBookingId())
                .status(booking.getStatus())
                .emailSent(true)
                .build();
    }
}
