package com.example.airbooking.controller;

import com.example.airbooking.model.*;
import com.example.airbooking.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;

/**
 * Controller for flight status and notification subscription APIs.
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
     * @param request FlightStatusRequest
     * @return FlightStatusResponse
     */
    @GetMapping("/flights/status")
    public ResponseEntity<FlightStatusResponse> getFlightStatus(@Valid FlightStatusRequest request) {
        FlightStatusResponse response = flightStatusService.getStatus(request);
        return ResponseEntity.ok(response);
    }

    /**
     * Subscribe to notifications for flight status changes.
     * @param request NotificationSubscribeRequest
     * @return NotificationSubscribeResponse
     */
    @PostMapping("/notifications/subscribe")
    public ResponseEntity<NotificationSubscribeResponse> subscribe(@Valid @RequestBody NotificationSubscribeRequest request) {
        NotificationSubscribeResponse response = notificationService.subscribe(request);
        return ResponseEntity.ok(response);
    }
}
