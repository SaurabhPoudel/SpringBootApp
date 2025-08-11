package com.example.firstAppBook.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
public class PaymentRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String paymentReference;
    private double amount;

    @ManyToOne
    private User user;

    private LocalDateTime paymentDate;
}
