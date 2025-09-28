package com.example.airlinebooking.service;

import com.example.airlinebooking.entity.Booking;
import com.example.airlinebooking.entity.Passenger;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Service for sending notifications (email/SMS).
 */
@Service
public class NotificationService {
    private static final Logger logger = LoggerFactory.getLogger(NotificationService.class);

    @Autowired
    private JavaMailSender mailSender;

    /**
     * Sends booking confirmation to all passengers via email.
     * @param booking booking entity
     */
    public void sendConfirmation(Booking booking) {
        for (Passenger passenger : booking.getPassengers()) {
            // Send email
            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(passenger.getEmail());
            message.setSubject("Booking Confirmation: " + booking.getBookingReference());
            message.setText("Dear " + passenger.getName() + ",\nYour booking is confirmed. Reference: " + booking.getBookingReference());
            try {
                mailSender.send(message);
                logger.info("Confirmation email sent to {}", passenger.getEmail());
            } catch (Exception e) {
                logger.error("Failed to send confirmation email to {}", passenger.getEmail(), e);
            }
            // For SMS: Integrate with SMS provider here (not implemented)
        }
    }
}
