package com.airbook.service;

import com.airbook.model.SupportRequest;
import org.springframework.stereotype.Service;

/**
 * Service for sending confirmations and updates for support requests
 */
@Service
public class NotificationService {
    /**
     * Send confirmation for support request (mock)
     */
    public void sendConfirmation(SupportRequest request) {
        System.out.println("Support confirmation sent for request: " + request.getRequestId());
    }
}
