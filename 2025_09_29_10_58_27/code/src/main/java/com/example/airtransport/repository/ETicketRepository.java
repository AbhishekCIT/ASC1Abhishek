package com.example.airtransport.repository;

import com.example.airtransport.entity.ETicket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository for ETicket entity.
 */
@Repository
public interface ETicketRepository extends JpaRepository<ETicket, String> {
}
