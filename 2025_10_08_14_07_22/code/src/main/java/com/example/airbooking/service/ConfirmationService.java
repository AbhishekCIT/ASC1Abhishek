package com.example.airbooking.service;

import com.example.airbooking.model.ConfirmationRequest;
import com.example.airbooking.model.ConfirmationResponse;
import com.example.airbooking.entity.Booking;
import com.example.airbooking.repository.BookingRepository;
import com.example.airbooking.exception.EmailException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Service for sending booking confirmation to user.
 */
@Service
public class ConfirmationService {

    @Autowired
    private BookingRepository bookingRepository;

    /**
     * Send booking confirmation to the user's email.
     * @param request the confirmation request
     * @return confirmation response
     */
    public ConfirmationResponse sendConfirmation(ConfirmationRequest request) {
        Booking booking = bookingRepository.findById(request.getBookingId())
                .orElseThrow(() -> new EmailException("Booking not found"));
        // TODO: Integrate with email service provider
        // Mock email sending
        if (request.getUserEmail() == null || !request.getUserEmail().contains("@")) {
            throw new EmailException("Invalid user email");
        }
        // Assume email sent successfully
        return new ConfirmationResponse("SENT");
    }
}
