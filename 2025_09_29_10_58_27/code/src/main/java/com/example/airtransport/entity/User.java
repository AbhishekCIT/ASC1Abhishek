package com.example.airtransport.entity;

import jakarta.persistence.*;
import lombok.Data;

/**
 * Entity for application user.
 */
@Entity
@Table(name = "users")
@Data
public class User {
    @Id
    private String id;
    @Column(nullable = false)
    private String email;
    @Column(nullable = false)
    private String name;
}
