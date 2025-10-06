package com.example.airbooking.repository;

import com.example.airbooking.model.ChatSession;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChatSessionRepository extends JpaRepository<ChatSession, String> {
    List<ChatSession> findByUserId(String userId);
    List<ChatSession> findByBookingId(String bookingId);
}