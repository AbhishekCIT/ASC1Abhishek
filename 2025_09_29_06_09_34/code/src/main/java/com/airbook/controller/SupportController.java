package com.airbook.controller;

import com.airbook.model.SupportRequest;
import com.airbook.model.SupportHistory;
import com.airbook.service.SupportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

/**
 * REST controller for support operations
 */
@RestController
@RequestMapping("/api/support")
public class SupportController {

    @Autowired
    private SupportService supportService;

    /**
     * Get FAQs for help center
     */
    @GetMapping("/faqs")
    public ResponseEntity<List<String>> getFaqs() {
        List<String> faqs = supportService.getFaqs();
        return ResponseEntity.ok(faqs);
    }

    /**
     * Create a support request
     */
    @PostMapping("/requests")
    public ResponseEntity<SupportRequest> createSupportRequest(@RequestBody SupportRequest request) {
        SupportRequest createdRequest = supportService.createSupportRequest(request);
        return ResponseEntity.ok(createdRequest);
    }

    /**
     * Get all support requests for a user
     */
    @GetMapping("/requests")
    public ResponseEntity<List<SupportRequest>> getSupportRequests(@RequestParam String userId) {
        List<SupportRequest> requests = supportService.getSupportRequests(userId);
        return ResponseEntity.ok(requests);
    }

    /**
     * Get status and history of a support request
     */
    @GetMapping("/requests/{requestId}")
    public ResponseEntity<SupportHistory> getSupportRequestStatus(@PathVariable String requestId) {
        SupportHistory history = supportService.getSupportRequestStatus(requestId);
        return ResponseEntity.ok(history);
    }
}
