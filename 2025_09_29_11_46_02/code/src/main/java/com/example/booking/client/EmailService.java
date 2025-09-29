package com.example.booking.client;

import com.example.booking.model.Booking;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * Simulates integration with an email service for sending booking confirmations.
 */
@Component
public class EmailService {
    private static final Logger logger = LoggerFactory.getLogger(EmailService.class);

    /**
     * Sends confirmation email with e-ticket (simulated).
     * @param booking Booking details
     */
    public void sendConfirmation(Booking booking) {
        logger.info("Sending confirmation email for booking: {} to passenger(s): {}", booking.getBookingReference(), booking.getPassengerDetails());
        // Simulate sending email
    }
}
