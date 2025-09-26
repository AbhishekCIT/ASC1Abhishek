package com.example.flightbooking.controller;

import com.example.flightbooking.model.*;
import com.example.flightbooking.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;

/**
 * Controller for flight status tracking and notification APIs.
 */
@RestController
@RequestMapping("/api")
public class FlightStatusController {

    @Autowired
    private FlightStatusService flightStatusService;

    @Autowired
    private NotificationService notificationService;

    /**
     * Get real-time flight status by flight number or booking reference.
     */
    @GetMapping("/flights/status")
    public ResponseEntity<?> getFlightStatus(@RequestParam(required = false) String flightNumber,
                                             @RequestParam(required = false) String bookingReference) {
        try {
            FlightStatus status = flightStatusService.getStatus(flightNumber, bookingReference);
            return ResponseEntity.ok(status);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    /**
     * Subscribe user to flight status notifications.
     */
    @PostMapping("/notifications/subscribe")
    public ResponseEntity<?> subscribeToNotifications(@Valid @RequestBody NotificationSubscriptionRequest request,
                                                      @RequestHeader("Authorization") String token) {
        try {
            NotificationSubscription subscription = notificationService.subscribe(request, token);
            return ResponseEntity.ok(subscription);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
}
