package com.example.calculator;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * Test class for CalculatorApplication.
 * Ensures that the Spring context loads and the main method runs without exceptions.
 */
@SpringBootTest
public class CalculatorApplicationTest {

    /**
     * Test to ensure the Spring Boot application context loads successfully.
     */
    @Test
    void contextLoads() {
        // Purpose: Verifies that the Spring context loads without errors.
    }

    /**
     * Test to ensure the main method runs without throwing exceptions.
     */
    @Test
    void testMainMethodRuns() {
        // Purpose: Verifies that the main method executes without exceptions.
        CalculatorApplication.main(new String[]{});
    }
}
