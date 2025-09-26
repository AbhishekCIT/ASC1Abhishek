package com.example.airtransport.service;

import com.example.airtransport.model.Booking;
import com.example.airtransport.model.BookingRequest;
import com.example.airtransport.repository.BookingRepository;
import com.example.airtransport.util.BookingReferenceGenerator;
import com.example.airtransport.util.ValidationUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;

/**
 * Service for booking creation and retrieval logic.
 */
@Service
public class BookingService {
    @Autowired
    private BookingRepository bookingRepository;
    @Autowired
    private BookingReferenceGenerator bookingReferenceGenerator;
    @Autowired
    private ValidationUtil validationUtil;

    /**
     * Create a new booking for a flight.
     * @param bookingRequest Booking request payload
     * @return Booking confirmation details
     */
    public Booking createBooking(BookingRequest bookingRequest) {
        // Validate passenger details
        validationUtil.validatePassengerDetails(bookingRequest.getPassengerDetails());
        // Generate unique booking reference
        String bookingReference = bookingReferenceGenerator.generateReference();
        // Create booking object
        Booking booking = new Booking();
        booking.setBookingReference(bookingReference);
        booking.setFlightId(bookingRequest.getFlightId());
        booking.setPassengerDetails(bookingRequest.getPassengerDetails());
        booking.setStatus("CONFIRMED");
        booking.setBookingTime(LocalDateTime.now());
        // Save booking
        bookingRepository.save(booking);
        return booking;
    }

    /**
     * Retrieve booking information by reference.
     * @param reference Booking reference
     * @return Booking details
     */
    public Booking getBookingByReference(String reference) {
        return bookingRepository.findByBookingReference(reference)
                .orElseThrow(() -> new RuntimeException("Booking reference not found"));
    }
}
