package com.example.airbooking.repository;

import com.example.airbooking.model.TicketUpdate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TicketUpdateRepository extends JpaRepository<TicketUpdate, String> {
    List<TicketUpdate> findByTicketId(String ticketId);
}