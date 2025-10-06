package com.example.airbooking.repository;

import com.example.airbooking.model.SupportTicket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SupportTicketRepository extends JpaRepository<SupportTicket, String> {
    List<SupportTicket> findByUserId(String userId);
    List<SupportTicket> findByBookingId(String bookingId);
}