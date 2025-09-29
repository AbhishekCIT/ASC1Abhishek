package com.airtransport.util;

import com.airtransport.model.FlightSearchRequest;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * Unit tests for LoggingService.
 * Uses Mockito to mock static LoggerFactory and Logger.
 */
public class LoggingServiceTest {
    private LoggingService loggingService;
    private MockedStatic<LoggerFactory> loggerFactoryMockedStatic;
    private Logger mockLogger;

    @BeforeEach
    void setUp() {
        mockLogger = mock(Logger.class);
        loggerFactoryMockedStatic = Mockito.mockStatic(LoggerFactory.class);
        loggerFactoryMockedStatic.when(() -> LoggerFactory.getLogger(LoggingService.class)).thenReturn(mockLogger);
        loggingService = new LoggingService();
    }

    @AfterEach
    void tearDown() {
        loggerFactoryMockedStatic.close();
    }

    @Test
    @DisplayName("logSearchQuery logs info for valid request")
    void testLogSearchQuery_Normal() {
        FlightSearchRequest request = new FlightSearchRequest();
        request.setUserId("user1");
        request.setSource("JFK");
        request.setDestination("LAX");
        request.setDate(LocalDate.of(2025, 12, 1));
        request.setPassengerCount(2);
        loggingService.logSearchQuery(request);
        verify(mockLogger, times(1)).info(anyString(), eq("user1"), eq("JFK"), eq("LAX"), eq(LocalDate.of(2025, 12, 1)), eq(2));
    }

    @Test
    @DisplayName("logSearchQuery handles null request (edge case)")
    void testLogSearchQuery_NullRequest() {
        assertThrows(NullPointerException.class, () -> loggingService.logSearchQuery(null));
    }

    @Test
    @DisplayName("logError logs error with exception")
    void testLogError_Normal() {
        Exception ex = new Exception("Test error");
        loggingService.logError("Error occurred", ex);
        verify(mockLogger, times(1)).error("Error occurred", ex);
    }

    @Test
    @DisplayName("logError handles null exception (edge case)")
    void testLogError_NullException() {
        loggingService.logError("Error occurred", null);
        verify(mockLogger, times(1)).error("Error occurred", (Exception) null);
    }
}
