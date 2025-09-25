package com.example.scheduler.service;

import com.example.scheduler.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * Unit tests for UserService.
 */
class UserServiceTest {
    @Mock
    private UserRepository userRepository;
    @InjectMocks
    private UserService userService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("Validate user - user exists")
    void testValidateUserExists() {
        when(userRepository.existsById(1L)).thenReturn(true);
        assertTrue(userService.validateUser(1L));
    }

    @Test
    @DisplayName("Validate user - user does not exist")
    void testValidateUserDoesNotExist() {
        when(userRepository.existsById(2L)).thenReturn(false);
        assertFalse(userService.validateUser(2L));
    }
}
