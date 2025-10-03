package com.example.airtransport.service;

import com.example.airtransport.entity.Booking;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

/**
 * Service for sending confirmation emails.
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class EmailService {
    private final JavaMailSender mailSender;

    @Value("${spring.mail.username:no-reply@airtransport.com}")
    private String fromEmail;

    /**
     * Sends a confirmation email to the user after booking.
     * @param toEmail Recipient's email address
     * @param booking Booking details
     * @return true if email sent successfully, false otherwise
     */
    public boolean sendConfirmationEmail(String toEmail, Booking booking) {
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom(fromEmail);
            message.setTo(toEmail);
            message.setSubject("Booking Confirmation - Air Transport");
            message.setText("Your booking (" + booking.getBookingId() + ") is confirmed.\nFlight: "
                    + booking.getFlight().getFlightId() + ", Status: " + booking.getStatus());
            mailSender.send(message);
            log.info("Confirmation email sent to {} for booking {}", toEmail, booking.getBookingId());
            return true;
        } catch (Exception e) {
            log.error("Failed to send confirmation email to {}: {}", toEmail, e.getMessage());
            return false;
        }
    }
}
