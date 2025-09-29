package com.example.calculator.exception;

import com.example.calculator.model.CalculationResponse;
import org.springframework.stereotype.Component;

/**
 * Component to handle errors and exceptions for calculator API
 */
@Component
public class ErrorHandler {
    /**
     * Handles known exceptions and returns CalculationResponse with error message
     * @param e Exception thrown
     * @return CalculationResponse with error
     */
    public CalculationResponse handleError(Exception e) {
        String errorMsg = e.getMessage();
        return new CalculationResponse(null, errorMsg);
    }
}
