package com.example.firstAppBook.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "payments")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // The user who made the payment
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    // Payment amount
    @Column(nullable = false)
    private Double amount;

    // When the payment was made
    @Column(nullable = false)
    private LocalDateTime paymentDate;

    // Payment status enum (PAID, FAILED, PENDING)
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private PaymentStatus paymentStatus;

    // Payment type enum (FINE, MEMBERSHIP, OTHER)
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private PaymentType paymentType;
    @Column(nullable = false)
    private String description;

}
