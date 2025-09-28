package com.example.calculator.controller;

import com.example.calculator.dto.CalculationRequest;
import com.example.calculator.dto.CalculationResponse;
import com.example.calculator.exception.DivisionByZeroException;
import com.example.calculator.exception.InvalidInputException;
import com.example.calculator.service.ArithmeticService;
import com.example.calculator.util.InputSanitizerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Controller to handle calculator operations as REST APIs.
 */
@RestController
@RequestMapping("/api/calculator")
public class CalculatorController {
    private static final Logger logger = LoggerFactory.getLogger(CalculatorController.class);

    @Autowired
    private ArithmeticService arithmeticService;

    @Autowired
    private InputSanitizerService inputSanitizerService;

    /**
     * Exposes the /calculate API for performing arithmetic operations.
     * @param request CalculationRequest containing num1, num2, and operation
     * @return CalculationResponse with result or error
     */
    @PostMapping("/calculate")
    public ResponseEntity<CalculationResponse> calculate(@RequestBody CalculationRequest request) {
        logger.info("Received calculation request: {}", request);
        CalculationResponse response = new CalculationResponse();
        try {
            // Validate and sanitize inputs
            double num1 = inputSanitizerService.sanitizeAndValidate(request.getNum1(), "num1");
            double num2 = inputSanitizerService.sanitizeAndValidate(request.getNum2(), "num2");
            String operation = request.getOperation();
            double result;
            switch (operation) {
                case "add":
                    result = arithmeticService.add(num1, num2);
                    break;
                case "subtract":
                    result = arithmeticService.subtract(num1, num2);
                    break;
                case "multiply":
                    result = arithmeticService.multiply(num1, num2);
                    break;
                case "divide":
                    result = arithmeticService.divide(num1, num2);
                    break;
                default:
                    logger.error("Invalid operation selected: {}", operation);
                    throw new InvalidInputException("Invalid operation selected.");
            }
            response.setResult(result);
            return ResponseEntity.ok(response);
        } catch (InvalidInputException | DivisionByZeroException ex) {
            logger.error("Calculation error: {}", ex.getMessage());
            response.setError(ex.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        } catch (Exception ex) {
            logger.error("Unexpected error: {}", ex.getMessage());
            response.setError("Internal server error.");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }
}
