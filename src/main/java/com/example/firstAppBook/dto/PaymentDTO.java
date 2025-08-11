package com.example.firstAppBook.dto;

import com.example.firstAppBook.entity.PaymentStatus;
import com.example.firstAppBook.entity.PaymentType;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PaymentDTO {
    private Long id;
    private Long userId;
    private Double amount;
    private LocalDateTime paymentDate;
    private PaymentStatus paymentStatus;
    private PaymentType paymentType;
}
