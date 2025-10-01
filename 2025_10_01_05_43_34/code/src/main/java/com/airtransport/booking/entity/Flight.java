package com.airtransport.booking.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "flights")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Flight {
    @Id
    private String id;

    @Column(nullable = false)
    private String airline;

    @Column(nullable = false)
    private String origin;

    @Column(nullable = false)
    private String destination;

    @Column(nullable = false)
    private LocalDateTime departure;

    @Column(nullable = false)
    private LocalDateTime arrival;

    @Column(nullable = false)
    private Double price;
}
