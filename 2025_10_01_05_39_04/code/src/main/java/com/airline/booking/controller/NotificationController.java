package com.airline.booking.controller;

import com.airline.booking.service.NotificationService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

/**
 * Controller for sending email notifications.
 */
@RestController
@RequestMapping("/api/notifications/email")
@RequiredArgsConstructor
public class NotificationController {
    private final NotificationService notificationService;

    /**
     * Send an email notification.
     */
    @PostMapping
    public ResponseEntity<EmailResponse> sendEmail(@Valid @RequestBody EmailRequest request) {
        notificationService.sendBookingConfirmation(request.getTo(), request.getSubject(), request.getBody());
        EmailResponse response = new EmailResponse();
        response.setStatus("SENT");
        return ResponseEntity.ok(response);
    }

    @Data
    public static class EmailRequest {
        private String to;
        private String subject;
        private String body;
    }

    @Data
    public static class EmailResponse {
        private String status;
    }
}
