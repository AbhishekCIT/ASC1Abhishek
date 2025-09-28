package com.example.airbooking.controller;

import com.example.airbooking.model.*;
import com.example.airbooking.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;

/**
 * Controller for baggage allowance, add, track, and notification APIs.
 */
@RestController
@RequestMapping("/api/baggage")
public class BaggageController {

    @Autowired
    private BaggageService baggageService;
    @Autowired
    private NotificationService notificationService;

    /**
     * Get baggage allowance for a booking.
     * @param request BaggageAllowanceRequest
     * @return BaggageAllowanceResponse
     */
    @GetMapping("/allowance")
    public ResponseEntity<BaggageAllowanceResponse> getAllowance(@Valid BaggageAllowanceRequest request) {
        BaggageAllowanceResponse response = baggageService.getAllowance(request);
        return ResponseEntity.ok(response);
    }

    /**
     * Add extra baggage and pay online.
     * @param request AddBaggageRequest
     * @return AddBaggageResponse
     */
    @PostMapping("/add")
    public ResponseEntity<AddBaggageResponse> addBaggage(@Valid @RequestBody AddBaggageRequest request) {
        AddBaggageResponse response = baggageService.addBaggage(request);
        return ResponseEntity.ok(response);
    }

    /**
     * Track the status of checked baggage.
     * @param request TrackBaggageRequest
     * @return TrackBaggageResponse
     */
    @GetMapping("/track")
    public ResponseEntity<TrackBaggageResponse> trackBaggage(@Valid TrackBaggageRequest request) {
        TrackBaggageResponse response = baggageService.trackBaggage(request);
        return ResponseEntity.ok(response);
    }

    /**
     * Subscribe to baggage status notifications.
     * @param request BaggageNotificationSubscribeRequest
     * @return NotificationSubscribeResponse
     */
    @PostMapping("/notifications/subscribe")
    public ResponseEntity<NotificationSubscribeResponse> subscribe(@Valid @RequestBody BaggageNotificationSubscribeRequest request) {
        NotificationSubscribeResponse response = notificationService.subscribeBaggage(request);
        return ResponseEntity.ok(response);
    }
}
