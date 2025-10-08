package com.example.calculator.repository;

import com.example.calculator.entity.CalculationLogEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository for CalculationLogEntity
 */
@Repository
public interface CalculationLogRepository extends JpaRepository<CalculationLogEntity, Long> {
}
