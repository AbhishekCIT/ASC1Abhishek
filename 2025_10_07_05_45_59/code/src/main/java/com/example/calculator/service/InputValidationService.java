package com.example.calculator.service;

import org.springframework.stereotype.Service;

/**
 * Service for validating numeric inputs for calculation requests.
 */
@Service
public class InputValidationService {
    /**
     * Validates if the provided numbers are valid (not NaN or infinite).
     * @param a first number
     * @param b second number
     * @return true if both numbers are valid, false otherwise
     */
    public boolean validateNumbers(double a, double b) {
        return !(Double.isNaN(a) || Double.isNaN(b) || Double.isInfinite(a) || Double.isInfinite(b));
    }
}
