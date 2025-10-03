package com.airtransport.service;

import com.airtransport.model.EmailRequest;
import com.airtransport.model.EmailResponse;
import org.springframework.stereotype.Service;

/**
 * Service for sending booking confirmation emails.
 */
@Service
public class EmailNotificationService {
    /**
     * Sends booking confirmation email.
     * @param bookingId Booking ID
     * @param email User email address
     * @return EmailResponse with notification status
     */
    public EmailResponse sendBookingConfirmation(String bookingId, String email) {
        // TODO: Integrate with email notification provider
        // For demo, assume email is sent successfully
        return new EmailResponse("SENT");
    }
}
