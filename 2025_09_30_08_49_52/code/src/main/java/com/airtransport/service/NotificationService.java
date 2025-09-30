package com.airtransport.service;

import org.springframework.stereotype.Service;

/**
 * Service for sending booking confirmations (mocked).
 */
@Service
public class NotificationService {
    /**
     * Send booking confirmation to the passenger.
     * @param email passenger email
     * @param bookingId booking ID
     */
    public void sendConfirmation(String email, String bookingId) {
        // Mocked logic: print to log
        System.out.printf("Confirmation sent to %s for booking %s\n", email, bookingId);
    }
}
