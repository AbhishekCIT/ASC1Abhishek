package com.example.calculator.controller;

import com.example.calculator.model.CalculationRequest;
import com.example.calculator.model.CalculationResponse;
import com.example.calculator.service.CalculatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * REST Controller for Calculator operations
 */
@RestController
@RequestMapping("/api")
public class CalculatorController {

    @Autowired
    private CalculatorService calculatorService;

    /**
     * Exposes the /calculate endpoint for arithmetic operations
     * @param request CalculationRequest containing num1, num2, and operation
     * @return CalculationResponse with result or error
     */
    @PostMapping("/calculate")
    public ResponseEntity<?> calculate(@RequestBody CalculationRequest request) {
        try {
            CalculationResponse response = calculatorService.calculate(request);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            CalculationResponse errorResponse = new CalculationResponse();
            errorResponse.setError(e.getMessage());
            return ResponseEntity.badRequest().body(errorResponse);
        }
    }
}
