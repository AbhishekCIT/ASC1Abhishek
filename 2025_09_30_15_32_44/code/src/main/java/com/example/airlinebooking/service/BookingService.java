package com.example.airlinebooking.service;

import com.example.airlinebooking.model.Booking;
import com.example.airlinebooking.model.BookingRequest;
import com.example.airlinebooking.model.Flight;
import com.example.airlinebooking.model.User;
import com.example.airlinebooking.repository.BookingRepository;
import com.example.airlinebooking.repository.FlightRepository;
import com.example.airlinebooking.repository.UserRepository;
import com.example.airlinebooking.util.BookingReferenceGenerator;
import com.example.airlinebooking.util.EmailService;
import com.example.airlinebooking.util.GDSIntegrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;

/**
 * Service for booking creation and management.
 */
@Service
public class BookingService {
    @Autowired
    private BookingRepository bookingRepository;
    @Autowired
    private FlightRepository flightRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private GDSIntegrationService gdsIntegrationService;
    @Autowired
    private BookingReferenceGenerator bookingReferenceGenerator;
    @Autowired
    private EmailService emailService;
    @Autowired
    private PaymentService paymentService;

    /**
     * Create a new booking for a user.
     * @param bookingRequest the booking request
     * @return the confirmed booking
     */
    public Booking createBooking(BookingRequest bookingRequest) {
        // Validate flight
        Flight flight = flightRepository.findById(bookingRequest.getFlightId())
                .orElseThrow(() -> new RuntimeException("Flight not found or unavailable"));
        // Validate user
        User user = userRepository.findById(bookingRequest.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));
        // Process payment
        boolean paymentSuccess = paymentService.processPayment(bookingRequest.getPaymentInfo(), flight.getPrice());
        if (!paymentSuccess) {
            throw new RuntimeException("Payment failed");
        }
        // Generate booking reference
        String bookingRef = bookingReferenceGenerator.generateRef();
        // Create booking
        Booking booking = new Booking();
        booking.setBookingRef(bookingRef);
        booking.setUserId(user.getId());
        booking.setFlightId(flight.getId());
        booking.setStatus("CONFIRMED");
        booking.setBookingTime(LocalDateTime.now());
        bookingRepository.save(booking);
        // Send confirmation email
        emailService.sendConfirmation(user.getEmail(), booking);
        // Optionally update user profile
        // ...
        return booking;
    }

    /**
     * Get booking by reference.
     * @param bookingRef the booking reference
     * @return booking
     */
    public Booking getBookingByReference(String bookingRef) {
        return bookingRepository.findByBookingRef(bookingRef)
                .orElseThrow(() -> new RuntimeException("Booking reference not found"));
    }
}
