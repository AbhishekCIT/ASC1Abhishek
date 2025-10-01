package com.example.airbooking.repository;

import com.example.airbooking.model.Booking;
import com.example.airbooking.model.BookingRequest;
import com.example.airbooking.model.PaymentResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository for Booking entity.
 */
@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {
    // Custom saveBooking method for business logic
    default Booking saveBooking(BookingRequest bookingRequest, PaymentResponse paymentResponse) {
        Booking booking = new Booking();
        booking.setFlightId(bookingRequest.getFlightId());
        booking.setUserId(1L); // Stub: replace with actual user
        booking.setStatus("CONFIRMED");
        booking.setBookingTime(java.time.Instant.now());
        // Additional fields from bookingRequest and paymentResponse
        return save(booking);
    }
}
