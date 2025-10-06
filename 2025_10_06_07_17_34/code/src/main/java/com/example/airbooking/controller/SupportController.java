package com.example.airbooking.controller;

import com.example.airbooking.model.SupportTicket;
import com.example.airbooking.model.ChatSession;
import com.example.airbooking.service.SupportService;
import com.example.airbooking.service.FAQService;
import com.example.airbooking.service.ChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * REST controller for support and FAQ endpoints.
 */
@RestController
@RequestMapping("/api/support")
public class SupportController {
    @Autowired
    private SupportService supportService;
    @Autowired
    private FAQService faqService;
    @Autowired
    private ChatService chatService;

    /**
     * Get FAQs.
     * @return List of FAQs
     */
    @GetMapping("/faqs")
    public List<Map<String, String>> getFaqs() {
        return faqService.getFaqs();
    }

    /**
     * Create support ticket.
     * @param ticketRequest Request body containing userId, bookingId, issueType, description
     * @return Created ticket
     */
    @PostMapping("/tickets")
    public SupportTicket createTicket(@RequestBody Map<String, String> ticketRequest) {
        String userId = ticketRequest.get("userId");
        String bookingId = ticketRequest.get("bookingId");
        String issueType = ticketRequest.get("issueType");
        String description = ticketRequest.get("description");
        return supportService.createTicket(userId, bookingId, issueType, description);
    }

    /**
     * Get ticket status by ticket ID.
     * @param ticketId Ticket ID
     * @return Ticket status
     */
    @GetMapping("/tickets/{ticketId}")
    public SupportTicket getTicketStatus(@PathVariable String ticketId) {
        return supportService.getTicketStatus(ticketId);
    }

    /**
     * Initiate live chat session.
     * @param chatRequest Request body containing userId and bookingId
     * @return ChatSession
     */
    @PostMapping("/chat/start")
    public ChatSession startChat(@RequestBody Map<String, String> chatRequest) {
        String userId = chatRequest.get("userId");
        String bookingId = chatRequest.get("bookingId");
        return chatService.startChat(userId, bookingId);
    }
}