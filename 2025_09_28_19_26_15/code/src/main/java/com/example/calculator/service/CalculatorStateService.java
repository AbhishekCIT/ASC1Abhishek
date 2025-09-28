package com.example.calculator.service;

import org.springframework.stereotype.Service;

/**
 * Service to manage and clear calculator state.
 */
@Service
public class CalculatorStateService {
    /**
     * Clears the calculator state. For stateless backend, this is a no-op.
     */
    public void clearState() {
        // No state to clear in backend; method provided for API completeness/logging.
    }
}
