package com.example.airbooking.repository;

import com.example.airbooking.entity.CheckIn;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository for CheckIn entity.
 */
@Repository
public interface CheckInRepository extends JpaRepository<CheckIn, String> {
}
