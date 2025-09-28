package com.example.calculator.controller;

import com.example.calculator.dto.*;
import com.example.calculator.exception.DivisionByZeroException;
import com.example.calculator.exception.InvalidInputException;
import com.example.calculator.exception.InvalidDecimalInputException;
import com.example.calculator.service.ArithmeticService;
import com.example.calculator.util.InputSanitizerService;
import com.example.calculator.util.DecimalInputValidatorService;
import com.example.calculator.service.CalculatorStateService;
import com.example.calculator.service.CalculationHistoryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

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

    @Autowired
    private DecimalInputValidatorService decimalInputValidatorService;

    @Autowired
    private CalculatorStateService calculatorStateService;

    @Autowired
    private CalculationHistoryService calculationHistoryService;

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
            // Validate decimal input
            decimalInputValidatorService.validateDecimal(request.getNum1());
            decimalInputValidatorService.validateDecimal(request.getNum2());
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
            // Round result to 4 decimal places
            double roundedResult = new BigDecimal(result).setScale(4, RoundingMode.HALF_UP).doubleValue();
            response.setResult(roundedResult);
            return ResponseEntity.ok(response);
        } catch (InvalidDecimalInputException ex) {
            logger.error("Invalid decimal input: {}", ex.getMessage());
            response.setError(ex.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
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

    /**
     * Exposes the /clear API for clearing calculator state.
     * @return ClearResponse with empty input and result fields
     */
    @PostMapping("/clear")
    public ResponseEntity<ClearResponse> clear() {
        logger.info("Received clear request.");
        calculatorStateService.clearState();
        ClearResponse response = new ClearResponse();
        response.setInput1("");
        response.setInput2("");
        response.setResult("");
        return ResponseEntity.ok(response);
    }

    /**
     * Returns the calculation history (up to 10 most recent calculations).
     */
    @GetMapping("/history")
    public ResponseEntity<HistoryResponse> getHistory() {
        List<CalculationHistoryItem> history = calculationHistoryService.getAll();
        HistoryResponse response = new HistoryResponse();
        response.setHistory(history);
        return ResponseEntity.ok(response);
    }

    /**
     * Adds a calculation to the history.
     */
    @PostMapping("/history")
    public ResponseEntity<StatusResponse> addCalculationToHistory(@RequestBody CalculationHistoryItem item) {
        calculationHistoryService.add(item);
        return ResponseEntity.ok(new StatusResponse("added"));
    }

    /**
     * Clears the calculation history.
     */
    @DeleteMapping("/history")
    public ResponseEntity<StatusResponse> clearHistory() {
        calculationHistoryService.clear();
        return ResponseEntity.ok(new StatusResponse("cleared"));
    }
}
