package com.example.calculator.controller;

import com.example.calculator.dto.*;
import com.example.calculator.exception.DivisionByZeroException;
import com.example.calculator.exception.InvalidDecimalInputException;
import com.example.calculator.exception.InvalidInputException;
import com.example.calculator.service.ArithmeticService;
import com.example.calculator.util.InputSanitizerService;
import com.example.calculator.util.DecimalInputValidatorService;
import com.example.calculator.service.CalculatorStateService;
import com.example.calculator.service.CalculationHistoryService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

/**
 * JUnit tests for CalculatorController.
 * Covers all endpoints, normal, edge, and error scenarios.
 */
class CalculatorControllerTest {

    @Mock
    private ArithmeticService arithmeticService;
    @Mock
    private InputSanitizerService inputSanitizerService;
    @Mock
    private DecimalInputValidatorService decimalInputValidatorService;
    @Mock
    private CalculatorStateService calculatorStateService;
    @Mock
    private CalculationHistoryService calculationHistoryService;

    @InjectMocks
    private CalculatorController calculatorController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    /**
     * Test normal addition operation.
     */
    @Test
    @DisplayName("POST /calculate - Addition Success")
    void testCalculateAdditionSuccess() {
        CalculationRequest request = new CalculationRequest("2", "3", "add");
        when(inputSanitizerService.sanitizeAndValidate("2", "num1")).thenReturn(2.0);
        when(inputSanitizerService.sanitizeAndValidate("3", "num2")).thenReturn(3.0);
        when(arithmeticService.add(2.0, 3.0)).thenReturn(5.0);
        doNothing().when(decimalInputValidatorService).validateDecimal(anyString());

        ResponseEntity<CalculationResponse> response = calculatorController.calculate(request);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(5.0, response.getBody().getResult());
        assertNull(response.getBody().getError());
    }

    /**
     * Test subtraction with negative result.
     */
    @Test
    @DisplayName("POST /calculate - Subtraction Negative Result")
    void testCalculateSubtractionNegative() {
        CalculationRequest request = new CalculationRequest("2", "5", "subtract");
        when(inputSanitizerService.sanitizeAndValidate("2", "num1")).thenReturn(2.0);
        when(inputSanitizerService.sanitizeAndValidate("5", "num2")).thenReturn(5.0);
        when(arithmeticService.subtract(2.0, 5.0)).thenReturn(-3.0);
        doNothing().when(decimalInputValidatorService).validateDecimal(anyString());

        ResponseEntity<CalculationResponse> response = calculatorController.calculate(request);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(-3.0, response.getBody().getResult());
        assertNull(response.getBody().getError());
    }

    /**
     * Test multiplication with decimals.
     */
    @Test
    @DisplayName("POST /calculate - Multiplication with Decimals")
    void testCalculateMultiplicationDecimals() {
        CalculationRequest request = new CalculationRequest("2.5", "4.2", "multiply");
        when(inputSanitizerService.sanitizeAndValidate("2.5", "num1")).thenReturn(2.5);
        when(inputSanitizerService.sanitizeAndValidate("4.2", "num2")).thenReturn(4.2);
        when(arithmeticService.multiply(2.5, 4.2)).thenReturn(10.5);
        doNothing().when(decimalInputValidatorService).validateDecimal(anyString());

        ResponseEntity<CalculationResponse> response = calculatorController.calculate(request);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(10.5, response.getBody().getResult());
        assertNull(response.getBody().getError());
    }

    /**
     * Test division with rounding to 4 decimal places.
     */
    @Test
    @DisplayName("POST /calculate - Division with Rounding")
    void testCalculateDivisionRounding() {
        CalculationRequest request = new CalculationRequest("10", "3", "divide");
        when(inputSanitizerService.sanitizeAndValidate("10", "num1")).thenReturn(10.0);
        when(inputSanitizerService.sanitizeAndValidate("3", "num2")).thenReturn(3.0);
        when(arithmeticService.divide(10.0, 3.0)).thenReturn(3.3333333);
        doNothing().when(decimalInputValidatorService).validateDecimal(anyString());

        ResponseEntity<CalculationResponse> response = calculatorController.calculate(request);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(3.3333, response.getBody().getResult()); // Rounded to 4 decimals
        assertNull(response.getBody().getError());
    }

    /**
     * Test division by zero triggers error.
     */
    @Test
    @DisplayName("POST /calculate - Division by Zero Error")
    void testCalculateDivisionByZero() {
        CalculationRequest request = new CalculationRequest("10", "0", "divide");
        when(inputSanitizerService.sanitizeAndValidate("10", "num1")).thenReturn(10.0);
        when(inputSanitizerService.sanitizeAndValidate("0", "num2")).thenReturn(0.0);
        doThrow(new DivisionByZeroException("Division by zero")).when(arithmeticService).divide(10.0, 0.0);
        doNothing().when(decimalInputValidatorService).validateDecimal(anyString());

        ResponseEntity<CalculationResponse> response = calculatorController.calculate(request);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertNull(response.getBody().getResult());
        assertEquals("Division by zero", response.getBody().getError());
    }

    /**
     * Test invalid decimal input triggers error.
     */
    @Test
    @DisplayName("POST /calculate - Invalid Decimal Input Error")
    void testCalculateInvalidDecimalInput() {
        CalculationRequest request = new CalculationRequest("2..5", "3", "add");
        doThrow(new InvalidDecimalInputException("Invalid decimal input")).when(decimalInputValidatorService).validateDecimal("2..5");

        ResponseEntity<CalculationResponse> response = calculatorController.calculate(request);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertNull(response.getBody().getResult());
        assertEquals("Invalid decimal input", response.getBody().getError());
    }

    /**
     * Test invalid operation triggers error.
     */
    @Test
    @DisplayName("POST /calculate - Invalid Operation Error")
    void testCalculateInvalidOperation() {
        CalculationRequest request = new CalculationRequest("2", "3", "modulo");
        when(inputSanitizerService.sanitizeAndValidate("2", "num1")).thenReturn(2.0);
        when(inputSanitizerService.sanitizeAndValidate("3", "num2")).thenReturn(3.0);
        doNothing().when(decimalInputValidatorService).validateDecimal(anyString());

        ResponseEntity<CalculationResponse> response = calculatorController.calculate(request);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertNull(response.getBody().getResult());
        assertEquals("Invalid operation selected.", response.getBody().getError());
    }

    /**
     * Test unexpected exception triggers internal server error.
     */
    @Test
    @DisplayName("POST /calculate - Unexpected Exception")
    void testCalculateUnexpectedException() {
        CalculationRequest request = new CalculationRequest("2", "3", "add");
        doThrow(new RuntimeException("Unexpected")).when(decimalInputValidatorService).validateDecimal(anyString());

        ResponseEntity<CalculationResponse> response = calculatorController.calculate(request);
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertNull(response.getBody().getResult());
        assertEquals("Internal server error.", response.getBody().getError());
    }

    /**
     * Test clear endpoint resets state.
     */
    @Test
    @DisplayName("POST /clear - Clears State")
    void testClear() {
        doNothing().when(calculatorStateService).clearState();
        ResponseEntity<ClearResponse> response = calculatorController.clear();
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("", response.getBody().getInput1());
        assertEquals("", response.getBody().getInput2());
        assertEquals("", response.getBody().getResult());
    }

    /**
     * Test getHistory returns calculation history.
     */
    @Test
    @DisplayName("GET /history - Returns Calculation History")
    void testGetHistory() {
        List<CalculationHistoryItem> history = new ArrayList<>();
        history.add(new CalculationHistoryItem("2", "add", "3", "5"));
        when(calculationHistoryService.getAll()).thenReturn(history);
        ResponseEntity<HistoryResponse> response = calculatorController.getHistory();
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(1, response.getBody().getHistory().size());
        assertEquals("5", response.getBody().getHistory().get(0).getResult());
    }

    /**
     * Test addCalculationToHistory adds item to history.
     */
    @Test
    @DisplayName("POST /history - Adds Calculation to History")
    void testAddCalculationToHistory() {
        CalculationHistoryItem item = new CalculationHistoryItem("2", "add", "3", "5");
        doNothing().when(calculationHistoryService).add(item);
        ResponseEntity<StatusResponse> response = calculatorController.addCalculationToHistory(item);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("added", response.getBody().getStatus());
    }

    /**
     * Test clearHistory clears calculation history.
     */
    @Test
    @DisplayName("DELETE /history - Clears Calculation History")
    void testClearHistory() {
        doNothing().when(calculationHistoryService).clear();
        ResponseEntity<StatusResponse> response = calculatorController.clearHistory();
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("cleared", response.getBody().getStatus());
    }

    /**
     * Test getHistory returns empty list when no history.
     */
    @Test
    @DisplayName("GET /history - Returns Empty History")
    void testGetHistoryEmpty() {
        when(calculationHistoryService.getAll()).thenReturn(Collections.emptyList());
        ResponseEntity<HistoryResponse> response = calculatorController.getHistory();
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertTrue(response.getBody().getHistory().isEmpty());
    }
}
