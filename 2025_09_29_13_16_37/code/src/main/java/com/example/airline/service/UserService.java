package com.example.airline.service;

import com.example.airline.model.User;
import com.example.airline.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public Optional<User> getUserById(String userId) {
        return userRepository.findById(userId);
    }
}