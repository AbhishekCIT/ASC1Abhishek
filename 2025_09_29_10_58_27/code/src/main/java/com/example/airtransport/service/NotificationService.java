package com.example.airtransport.service;

import com.example.airtransport.model.EmailRequest;
import com.example.airtransport.model.EmailResponse;
import com.example.airtransport.repository.BookingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

/**
 * Service to send booking confirmation emails and generate e-tickets.
 */
@Service
@RequiredArgsConstructor
public class NotificationService {
    private final JavaMailSender mailSender;
    private final BookingRepository bookingRepository;

    @Value("${app.eticket.base-url:https://etickets.example.com/}")
    private String eticketBaseUrl;

    /**
     * Send booking confirmation email with e-ticket link.
     */
    public EmailResponse sendBookingConfirmationEmail(EmailRequest request) {
        var booking = bookingRepository.findById(request.getBookingId())
                .orElseThrow(() -> new IllegalArgumentException("Invalid booking ID"));
        String eticketUrl = eticketBaseUrl + booking.getId() + ".pdf";
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(request.getEmail());
        message.setSubject("Your Booking Confirmation");
        message.setText("Thank you for booking. Your e-ticket: " + eticketUrl);
        mailSender.send(message);
        EmailResponse response = new EmailResponse();
        response.setStatus("SENT");
        response.setETicketUrl(eticketUrl);
        return response;
    }
}
