package com.example.flightbooking.controller;

import com.example.flightbooking.model.*;
import com.example.flightbooking.service.SupportRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;

/**
 * Controller for customer support requests.
 */
@RestController
@RequestMapping("/api/support/requests")
public class SupportRequestController {

    @Autowired
    private SupportRequestService supportRequestService;

    /**
     * Create a support request (chat/email/phone).
     */
    @PostMapping
    public ResponseEntity<?> createSupportRequest(@Valid @RequestBody SupportRequestInput request,
                                                  @RequestHeader("Authorization") String token) {
        try {
            SupportRequestResponse response = supportRequestService.createRequest(request, token);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    /**
     * Get the status of a support request.
     */
    @GetMapping("/{supportRequestId}")
    public ResponseEntity<?> getSupportRequestStatus(@PathVariable String supportRequestId,
                                                     @RequestHeader("Authorization") String token) {
        try {
            SupportRequestStatusResponse response = supportRequestService.getRequestStatus(supportRequestId, token);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    /**
     * Escalate a support request to a human agent.
     */
    @PostMapping("/{supportRequestId}/escalate")
    public ResponseEntity<?> escalateSupportRequest(@PathVariable String supportRequestId,
                                                   @RequestHeader("Authorization") String token) {
        try {
            SupportRequestEscalationResponse response = supportRequestService.escalateRequest(supportRequestId, token);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
}
