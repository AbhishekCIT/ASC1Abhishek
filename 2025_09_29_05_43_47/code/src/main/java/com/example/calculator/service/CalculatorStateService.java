package com.example.calculator.service;

import com.example.calculator.entity.CalculationState;
import com.example.calculator.repository.CalculationStateRepository;
import com.example.calculator.exception.ResetFailedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.time.LocalDateTime;

/**
 * Service to manage calculator UI state (reset functionality)
 */
@Service
public class CalculatorStateService {
    private static final Logger logger = LoggerFactory.getLogger(CalculatorStateService.class);

    @Autowired
    private CalculationStateRepository calculationStateRepository;

    /**
     * Resets calculator state (inputs, result, errors)
     */
    public void resetState() {
        try {
            CalculationState state = new CalculationState();
            state.setInputs("");
            state.setResult("");
            state.setErrors("");
            state.setTimestamp(LocalDateTime.now());
            calculationStateRepository.save(state);
            logger.info("Calculator state reset successfully");
        } catch (Exception ex) {
            logger.error("Unable to reset calculator state: {}", ex.getMessage());
            throw new ResetFailedException("Unable to reset calculator.");
        }
    }
}
