package com.airtransport.service;

import com.airtransport.entity.Booking;
import org.springframework.stereotype.Service;
import java.util.HashSet;
import java.util.Set;

/**
 * NotificationService sends confirmation emails to users.
 */
@Service
public class NotificationService {
    private Set<Long> sentConfirmations = new HashSet<>();

    /**
     * Sends confirmation email to the user for the booking.
     * Returns true if the email was sent successfully.
     */
    public boolean sendConfirmation(Long userId, Booking booking) {
        // TODO: Integrate with real email service
        sentConfirmations.add(booking.getId());
        return true;
    }

    /**
     * Checks if confirmation email was sent for the booking.
     */
    public boolean wasConfirmationSent(Long bookingId) {
        return sentConfirmations.contains(bookingId);
    }
}
