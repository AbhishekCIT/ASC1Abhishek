package com.airbook.service;

import com.airbook.model.SupportRequest;
import com.airbook.model.SupportHistory;
import com.airbook.repository.SupportRepository;
import com.airbook.service.FAQService;
import com.airbook.service.TicketingService;
import com.airbook.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

/**
 * Service for support logic and orchestration
 */
@Service
public class SupportService {
    @Autowired
    private FAQService faqService;
    @Autowired
    private TicketingService ticketingService;
    @Autowired
    private NotificationService notificationService;
    @Autowired
    private SupportRepository supportRepository;

    /**
     * Get FAQs for help center
     */
    public List<String> getFaqs() {
        return faqService.fetchFaqs();
    }

    /**
     * Create a support request
     */
    public SupportRequest createSupportRequest(SupportRequest request) {
        if (request.getUserId() == null || request.getType() == null || request.getSubject() == null || request.getMessage() == null) {
            throw new IllegalArgumentException("Invalid or missing request details");
        }
        SupportRequest ticket = ticketingService.createTicket(request);
        notificationService.sendConfirmation(ticket);
        return ticket;
    }

    /**
     * Get all support requests for a user
     */
    public List<SupportRequest> getSupportRequests(String userId) {
        return supportRepository.findRequestsByUser(userId);
    }

    /**
     * Get status and history of a support request
     */
    public SupportHistory getSupportRequestStatus(String requestId) {
        return ticketingService.getSupportHistory(requestId);
    }
}
