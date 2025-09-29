package com.airbook.service;

import com.airbook.model.Booking;
import com.airbook.model.Refund;
import com.airbook.repository.BookingRepository;
import com.airbook.service.AirlineIntegrationService;
import com.airbook.service.RefundService;
import com.airbook.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.UUID;

/**
 * Service for booking management, modification, cancellation, and refund
 */
@Service
public class BookingService {

    @Autowired
    private BookingRepository bookingRepository;
    @Autowired
    private AirlineIntegrationService airlineIntegrationService;
    @Autowired
    private RefundService refundService;
    @Autowired
    private NotificationService notificationService;

    /**
     * Get all bookings for a user
     */
    public List<Booking> getBookings(String userId) {
        // Authenticated user validation should be handled by security config
        return bookingRepository.findBookingsByUser(userId);
    }

    /**
     * Modify a booking if allowed by airline policy
     */
    public Booking modifyBooking(String bookingId, String newDate, String seat) {
        Booking booking = bookingRepository.findById(bookingId);
        if (booking == null) {
            throw new IllegalArgumentException("Booking not found");
        }
        if (!airlineIntegrationService.isModificationAllowed(booking)) {
            throw new IllegalArgumentException("Modification not allowed by airline policy");
        }
        booking.setBookingDate(newDate);
        booking.setSeat(seat);
        booking.setStatus("MODIFIED");
        bookingRepository.save(booking);
        notificationService.sendModificationNotification(booking);
        return booking;
    }

    /**
     * Cancel a booking if allowed by airline policy and process refund
     */
    public Refund cancelBooking(String bookingId) {
        Booking booking = bookingRepository.findById(bookingId);
        if (booking == null) {
            throw new IllegalArgumentException("Booking not found");
        }
        if (!airlineIntegrationService.isCancellationAllowed(booking)) {
            throw new IllegalArgumentException("Cancellation not allowed by airline policy");
        }
        booking.setStatus("CANCELLED");
        bookingRepository.save(booking);
        Refund refund = refundService.processRefund(booking);
        notificationService.sendCancellationNotification(booking);
        return refund;
    }

    /**
     * Get booking history for a user
     */
    public List<Booking> getBookingHistory(String userId) {
        return bookingRepository.findBookingHistoryByUser(userId);
    }
}
