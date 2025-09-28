package com.example.airbooking.repository;

import com.example.airbooking.entity.SpecialRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository for SpecialRequest entity.
 */
@Repository
public interface SpecialRequestRepository extends JpaRepository<SpecialRequest, String> {
}
