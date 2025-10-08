package com.example.calculator.controller;

import com.example.calculator.model.CalculationRequest;
import com.example.calculator.model.CalculationResponse;
import com.example.calculator.model.ErrorResponse;
import com.example.calculator.service.CalculatorService;
import com.example.calculator.util.InputValidatorUtil;
import com.example.calculator.exception.InvalidInputException;
import com.example.calculator.exception.DivisionByZeroException;
import com.example.calculator.exception.OperationNotSupportedException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * REST Controller for calculator operations.
 */
@RestController
@RequestMapping("/api")
public class CalculatorController {
    private static final Logger logger = LoggerFactory.getLogger(CalculatorController.class);

    @Autowired
    private CalculatorService calculatorService;

    @Autowired
    private InputValidatorUtil inputValidatorUtil;

    /**
     * Exposes the calculation API endpoint.
     * @param request CalculationRequest containing number1, number2, and operation
     * @return CalculationResponse or ErrorResponse
     */
    @PostMapping("/calculate")
    public ResponseEntity<?> calculate(@RequestBody CalculationRequest request) {
        try {
            inputValidatorUtil.validateInputs(request.getNumber1(), request.getNumber2(), request.getOperation());
            Double result;
            switch (request.getOperation().toLowerCase()) {
                case "add":
                    result = calculatorService.add(request.getNumber1(), request.getNumber2());
                    break;
                case "subtract":
                    result = calculatorService.subtract(request.getNumber1(), request.getNumber2());
                    break;
                case "multiply":
                    result = calculatorService.multiply(request.getNumber1(), request.getNumber2());
                    break;
                case "divide":
                    result = calculatorService.divide(request.getNumber1(), request.getNumber2());
                    break;
                default:
                    throw new OperationNotSupportedException("Invalid operation");
            }
            return ResponseEntity.ok(new CalculationResponse(result));
        } catch (InvalidInputException | DivisionByZeroException | OperationNotSupportedException ex) {
            logger.warn("Calculation error: {}", ex.getMessage());
            return ResponseEntity.badRequest().body(new ErrorResponse(ex.getMessage()));
        } catch (Exception ex) {
            logger.error("Unexpected error: {}", ex.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ErrorResponse("Internal server error"));
        }
    }
}
