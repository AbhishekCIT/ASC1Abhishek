package com.airtransport.booking.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class EmailService {
    private final JavaMailSender mailSender;

    @Value("${spring.mail.username}")
    private String fromEmail;

    /**
     * Sends booking confirmation and e-ticket to the user's email.
     */
    public void sendBookingConfirmation(String toEmail, String bookingRef, String eTicket) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(fromEmail);
        message.setTo(toEmail);
        message.setSubject("Booking Confirmation - " + bookingRef);
        message.setText("Your booking is confirmed.\nBooking Ref: " + bookingRef + "\nE-Ticket: " + eTicket);
        mailSender.send(message);
        log.info("Booking confirmation email sent to {} for bookingRef {}", toEmail, bookingRef);
    }
}
