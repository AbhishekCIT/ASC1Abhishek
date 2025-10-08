package com.example.calculator.controller;

import com.example.calculator.model.CalculationRequest;
import com.example.calculator.model.CalculationResponse;
import com.example.calculator.model.ErrorResponse;
import com.example.calculator.service.CalculatorService;
import com.example.calculator.util.InputValidatorUtil;
import com.example.calculator.exception.InvalidInputException;
import com.example.calculator.exception.DivisionByZeroException;
import com.example.calculator.exception.OperationNotSupportedException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * Test class for CalculatorController.
 * Purpose: Test REST endpoint for all scenarios, including error handling.
 */
public class CalculatorControllerTest {
    @Mock
    private CalculatorService calculatorService;

    @Mock
    private InputValidatorUtil inputValidatorUtil;

    @InjectMocks
    private CalculatorController calculatorController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    /**
     * Test successful addition operation.
     */
    @Test
    void testCalculate_Addition_Success() {
        CalculationRequest request = new CalculationRequest(2.0, 3.0, "add");
        doNothing().when(inputValidatorUtil).validateInputs(2.0, 3.0, "add");
        when(calculatorService.add(2.0, 3.0)).thenReturn(5.0);
        ResponseEntity<?> response = calculatorController.calculate(request);
        assertTrue(response.getBody() instanceof CalculationResponse);
        assertEquals(5.0, ((CalculationResponse) response.getBody()).getResult());
    }

    /**
     * Test successful subtraction operation.
     */
    @Test
    void testCalculate_Subtraction_Success() {
        CalculationRequest request = new CalculationRequest(5.0, 2.0, "subtract");
        doNothing().when(inputValidatorUtil).validateInputs(5.0, 2.0, "subtract");
        when(calculatorService.subtract(5.0, 2.0)).thenReturn(3.0);
        ResponseEntity<?> response = calculatorController.calculate(request);
        assertTrue(response.getBody() instanceof CalculationResponse);
        assertEquals(3.0, ((CalculationResponse) response.getBody()).getResult());
    }

    /**
     * Test successful multiplication operation.
     */
    @Test
    void testCalculate_Multiplication_Success() {
        CalculationRequest request = new CalculationRequest(2.0, 3.0, "multiply");
        doNothing().when(inputValidatorUtil).validateInputs(2.0, 3.0, "multiply");
        when(calculatorService.multiply(2.0, 3.0)).thenReturn(6.0);
        ResponseEntity<?> response = calculatorController.calculate(request);
        assertTrue(response.getBody() instanceof CalculationResponse);
        assertEquals(6.0, ((CalculationResponse) response.getBody()).getResult());
    }

    /**
     * Test successful division operation.
     */
    @Test
    void testCalculate_Division_Success() {
        CalculationRequest request = new CalculationRequest(6.0, 3.0, "divide");
        doNothing().when(inputValidatorUtil).validateInputs(6.0, 3.0, "divide");
        when(calculatorService.divide(6.0, 3.0)).thenReturn(2.0);
        ResponseEntity<?> response = calculatorController.calculate(request);
        assertTrue(response.getBody() instanceof CalculationResponse);
        assertEquals(2.0, ((CalculationResponse) response.getBody()).getResult());
    }

    /**
     * Test division by zero error handling.
     */
    @Test
    void testCalculate_DivisionByZero_Error() {
        CalculationRequest request = new CalculationRequest(5.0, 0.0, "divide");
        doNothing().when(inputValidatorUtil).validateInputs(5.0, 0.0, "divide");
        when(calculatorService.divide(5.0, 0.0)).thenThrow(new DivisionByZeroException("Division by zero is not allowed."));
        ResponseEntity<?> response = calculatorController.calculate(request);
        assertTrue(response.getBody() instanceof ErrorResponse);
        assertEquals("Division by zero is not allowed.", ((ErrorResponse) response.getBody()).getError());
    }

    /**
     * Test invalid input error handling.
     */
    @Test
    void testCalculate_InvalidInput_Error() {
        CalculationRequest request = new CalculationRequest(null, 2.0, "add");
        doThrow(new InvalidInputException("Inputs cannot be empty")).when(inputValidatorUtil).validateInputs(null, 2.0, "add");
        ResponseEntity<?> response = calculatorController.calculate(request);
        assertTrue(response.getBody() instanceof ErrorResponse);
        assertEquals("Inputs cannot be empty", ((ErrorResponse) response.getBody()).getError());
    }

    /**
     * Test unsupported operation error handling.
     */
    @Test
    void testCalculate_UnsupportedOperation_Error() {
        CalculationRequest request = new CalculationRequest(2.0, 3.0, "mod");
        doNothing().when(inputValidatorUtil).validateInputs(2.0, 3.0, "mod");
        ResponseEntity<?> response = calculatorController.calculate(request);
        assertTrue(response.getBody() instanceof ErrorResponse);
        assertEquals("Invalid operation", ((ErrorResponse) response.getBody()).getError());
    }

    /**
     * Test unexpected exception handling.
     */
    @Test
    void testCalculate_UnexpectedException_Error() {
        CalculationRequest request = new CalculationRequest(2.0, 3.0, "add");
        doNothing().when(inputValidatorUtil).validateInputs(2.0, 3.0, "add");
        when(calculatorService.add(2.0, 3.0)).thenThrow(new RuntimeException("Unexpected error"));
        ResponseEntity<?> response = calculatorController.calculate(request);
        assertTrue(response.getBody() instanceof ErrorResponse);
        assertEquals("Internal server error", ((ErrorResponse) response.getBody()).getError());
    }
}
