package com.example.calculator.repository;

import com.example.calculator.entity.UIEvent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository for UIEvent entity
 */
@Repository
public interface UIEventRepository extends JpaRepository<UIEvent, Integer> {
    // Additional query methods can be defined here if needed
}
