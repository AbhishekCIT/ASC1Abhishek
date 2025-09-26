package com.example.flightbooking.service;

import com.example.flightbooking.model.Booking;
import org.springframework.stereotype.Service;

/**
 * Service for sending booking confirmations to users.
 */
@Service
public class NotificationService {

    /**
     * Sends booking confirmation notification to the user.
     * @param booking the booking details
     */
    public void sendBookingConfirmation(Booking booking) {
        // TODO: Integrate with actual notification service (email/SMS/push)
        System.out.println("Booking confirmation sent to user: " + booking.getUserId() + ", bookingId: " + booking.getBookingId());
    }
}
