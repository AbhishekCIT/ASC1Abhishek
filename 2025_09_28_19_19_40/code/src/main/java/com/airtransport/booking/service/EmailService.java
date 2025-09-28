package com.airtransport.booking.service;

import com.airtransport.booking.entity.Booking;
import com.airtransport.booking.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

/**
 * Service for sending booking confirmation emails.
 */
@Service
public class EmailService {
    @Autowired
    private JavaMailSender mailSender;

    /**
     * Sends booking confirmation email to the user.
     * @param booking Booking details
     * @param user User details
     */
    public void sendBookingConfirmation(Booking booking, User user) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(user.getEmail());
        message.setSubject("Booking Confirmation: " + booking.getBookingRef());
        message.setText("Dear " + user.getName() + ",\n\nYour booking is confirmed. Reference: " + booking.getBookingRef() + ".\nStatus: " + booking.getStatus() + "\n\nThank you for booking with us!");
        mailSender.send(message);
    }
}
