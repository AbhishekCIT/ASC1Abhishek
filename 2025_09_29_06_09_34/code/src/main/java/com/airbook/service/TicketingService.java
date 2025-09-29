package com.airbook.service;

import com.airbook.model.SupportRequest;
import com.airbook.model.SupportHistory;
import com.airbook.repository.SupportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.UUID;

/**
 * Service for managing support tickets and requests
 */
@Service
public class TicketingService {
    @Autowired
    private SupportRepository supportRepository;

    /**
     * Create a support ticket
     */
    public SupportRequest createTicket(SupportRequest request) {
        request.setRequestId(UUID.randomUUID().toString());
        request.setStatus("OPEN");
        request.setCreatedAt(java.time.LocalDateTime.now());
        supportRepository.save(request);
        return request;
    }

    /**
     * Get support history for a request (mock)
     */
    public SupportHistory getSupportHistory(String requestId) {
        SupportHistory history = new SupportHistory();
        history.setHistoryId(UUID.randomUUID().toString());
        history.setRequestId(requestId);
        history.setAction("RESOLVED");
        history.setPerformedBy("SupportAgent");
        history.setActionAt(java.time.LocalDateTime.now());
        return history;
    }
}
