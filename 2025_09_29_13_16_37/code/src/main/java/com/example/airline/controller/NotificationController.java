package com.example.airline.controller;

import com.example.airline.dto.NotificationRequest;
import com.example.airline.dto.NotificationResponse;
import com.example.airline.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/notifications")
public class NotificationController {
    @Autowired
    private NotificationService notificationService;

    /**
     * Send a notification (email/SMS) to the user.
     * @param request NotificationRequest
     * @return NotificationResponse
     */
    @PostMapping("/send")
    public ResponseEntity<NotificationResponse> sendNotification(@RequestBody NotificationRequest request) {
        notificationService.sendNotification(request.getUserId(), request.getType(), request.getMessage());
        NotificationResponse response = new NotificationResponse();
        response.setNotificationStatus("SENT");
        return ResponseEntity.ok(response);
    }
}