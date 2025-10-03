package com.example.airtransport.service;

import com.example.airtransport.model.BookingResponse;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Service for sending confirmation emails.
 */
@Service
public class EmailService {
    private static final Logger logger = LoggerFactory.getLogger(EmailService.class);

    /**
     * Sends a confirmation email for the booking.
     * @param response Booking confirmation response
     */
    public void sendConfirmationEmail(BookingResponse response) {
        // Simulate sending email (should call real email service in production)
        logger.info("Confirmation email sent for booking reference: {}", response.getBookingReference());
    }
}
