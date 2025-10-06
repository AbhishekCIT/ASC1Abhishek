package com.example.airbooking.service;

import com.example.airbooking.model.SupportTicket;
import com.example.airbooking.model.TicketUpdate;
import com.example.airbooking.repository.SupportTicketRepository;
import com.example.airbooking.repository.TicketUpdateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

/**
 * Service for support ticket management.
 */
@Service
public class SupportService {
    @Autowired
    private SupportTicketRepository supportTicketRepository;
    @Autowired
    private TicketUpdateRepository ticketUpdateRepository;

    /**
     * Creates a support ticket.
     * @param userId User ID
     * @param bookingId Booking ID
     * @param issueType Issue type
     * @param description Issue description
     * @return Created SupportTicket
     */
    public SupportTicket createTicket(String userId, String bookingId, String issueType, String description) {
        if (userId == null || userId.isEmpty() || bookingId == null || bookingId.isEmpty() || issueType == null || issueType.isEmpty() || description == null || description.isEmpty()) {
            throw new IllegalArgumentException("Missing user or booking details.");
        }
        SupportTicket ticket = new SupportTicket();
        ticket.setTicketId(UUID.randomUUID().toString());
        ticket.setUserId(userId);
        ticket.setBookingId(bookingId);
        ticket.setIssueType(issueType);
        ticket.setStatus("OPEN");
        ticket.setCreatedAt(LocalDateTime.now());
        return supportTicketRepository.save(ticket);
    }

    /**
     * Gets ticket status and updates by ticket ID.
     * @param ticketId Ticket ID
     * @return SupportTicket with updates
     */
    public SupportTicket getTicketStatus(String ticketId) {
        SupportTicket ticket = supportTicketRepository.findById(ticketId).orElseThrow(() -> new RuntimeException("Ticket not found."));
        List<TicketUpdate> updates = ticketUpdateRepository.findByTicketId(ticketId);
        // For demonstration, you could set updates in the ticket if modeled as a relationship
        return ticket;
    }
}