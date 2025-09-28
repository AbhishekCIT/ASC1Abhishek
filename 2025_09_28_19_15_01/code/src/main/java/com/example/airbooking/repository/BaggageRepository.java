package com.example.airbooking.repository;

import com.example.airbooking.entity.Baggage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository for Baggage entity.
 */
@Repository
public interface BaggageRepository extends JpaRepository<Baggage, String> {
}
