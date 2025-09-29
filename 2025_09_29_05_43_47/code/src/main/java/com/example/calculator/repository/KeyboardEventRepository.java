package com.example.calculator.repository;

import com.example.calculator.entity.KeyboardEvent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository for KeyboardEvent entity
 */
@Repository
public interface KeyboardEventRepository extends JpaRepository<KeyboardEvent, Integer> {
    // Additional query methods can be defined here if needed
}
