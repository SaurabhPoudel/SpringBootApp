package com.example.firstAppBook.service.impl;

import com.example.firstAppBook.dto.PaymentDTO;
import com.example.firstAppBook.entity.Payment;
import com.example.firstAppBook.entity.User;
import com.example.firstAppBook.exception.ResourceNotFoundException;
import com.example.firstAppBook.repository.PaymentRepository;
import com.example.firstAppBook.repository.UserRepository;
import com.example.firstAppBook.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {
    private final PaymentRepository paymentRepository;
    private final UserRepository userRepository;

    @Override
    public PaymentDTO makePayment(PaymentDTO paymentDTO) {
        User user = userRepository.findById(paymentDTO.getUserId())
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        Payment payment = Payment.builder()
                .user(user)
                .amount(paymentDTO.getAmount())
                .paymentDate(LocalDateTime.now())
                .paymentStatus(paymentDTO.getPaymentStatus())
                .paymentType(paymentDTO.getPaymentType())
                .build();

        Payment savedPayment = paymentRepository.save(payment);

        return mapToDTO(savedPayment);
    }

    @Override
    public List<PaymentDTO> getPaymentsByUserId(Long userId) {
        return paymentRepository.findByUserId(userId).stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public PaymentDTO getPaymentById(Long paymentId) {
        Payment payment = paymentRepository.findById(paymentId)
                .orElseThrow(() -> new ResourceNotFoundException("Payment not found"));
        return mapToDTO(payment);
    }

    private PaymentDTO mapToDTO(Payment payment) {
        return PaymentDTO.builder()
                .id(payment.getId())
                .userId(payment.getUser().getId())
                .amount(payment.getAmount())
                .paymentDate(payment.getPaymentDate())
                .paymentStatus(payment.getPaymentStatus())
                .paymentType(payment.getPaymentType())
                .build();
    }
}
