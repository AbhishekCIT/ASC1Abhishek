package com.airbook.service;

import com.airbook.model.Booking;
import org.springframework.stereotype.Service;

/**
 * Service for sending notifications for booking modifications/cancellations
 */
@Service
public class NotificationService {
    /**
     * Send modification notification (mock)
     */
    public void sendModificationNotification(Booking booking) {
        System.out.println("Modification notification sent for booking: " + booking.getBookingId());
    }

    /**
     * Send cancellation notification (mock)
     */
    public void sendCancellationNotification(Booking booking) {
        System.out.println("Cancellation notification sent for booking: " + booking.getBookingId());
    }
}
