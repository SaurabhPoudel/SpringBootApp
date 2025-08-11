package com.example.firstAppBook.dto;

import com.example.firstAppBook.entity.Payment;
import com.example.firstAppBook.entity.User;
import org.springframework.stereotype.Component;

@Component
public class PaymentMapper {

    public PaymentDTO toDTO(Payment payment) {
        if (payment == null) return null;

        return PaymentDTO.builder()
                .id(payment.getId())
                .userId(payment.getUser() != null ? payment.getUser().getId() : null)
                .amount(payment.getAmount())
                .paymentDate(payment.getPaymentDate())
                .paymentStatus(payment.getPaymentStatus())
                .paymentType(payment.getPaymentType())
                .build();
    }

    public Payment toEntity(PaymentDTO paymentDTO, User user) {
        if (paymentDTO == null) return null;

        return Payment.builder()
                .id(paymentDTO.getId())
                .user(user)
                .amount(paymentDTO.getAmount())
                .paymentDate(paymentDTO.getPaymentDate())
                .paymentStatus(paymentDTO.getPaymentStatus())
                .paymentType(paymentDTO.getPaymentType())
                .build();
    }
}