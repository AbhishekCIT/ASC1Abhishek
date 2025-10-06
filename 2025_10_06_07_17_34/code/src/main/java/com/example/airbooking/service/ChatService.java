package com.example.airbooking.service;

import com.example.airbooking.model.ChatSession;
import com.example.airbooking.repository.ChatSessionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * Service for live chat integration.
 */
@Service
public class ChatService {
    @Autowired
    private ChatSessionRepository chatSessionRepository;

    /**
     * Starts a live chat session.
     * @param userId User ID
     * @param bookingId Booking ID
     * @return ChatSession
     */
    public ChatSession startChat(String userId, String bookingId) {
        if (userId == null || userId.isEmpty() || bookingId == null || bookingId.isEmpty()) {
            throw new IllegalArgumentException("Unable to start live chat session.");
        }
        ChatSession chatSession = new ChatSession();
        chatSession.setChatSessionId(UUID.randomUUID().toString());
        chatSession.setUserId(userId);
        chatSession.setBookingId(bookingId);
        chatSession.setStatus("ACTIVE");
        chatSession.setStartedAt(LocalDateTime.now());
        return chatSessionRepository.save(chatSession);
    }
}