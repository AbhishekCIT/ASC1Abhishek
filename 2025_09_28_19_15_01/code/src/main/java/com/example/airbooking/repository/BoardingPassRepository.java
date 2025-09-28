package com.example.airbooking.repository;

import com.example.airbooking.entity.BoardingPass;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository for BoardingPass entity.
 */
@Repository
public interface BoardingPassRepository extends JpaRepository<BoardingPass, String> {
}
