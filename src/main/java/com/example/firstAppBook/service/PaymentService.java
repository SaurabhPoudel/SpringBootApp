package com.example.firstAppBook.service;

import com.example.firstAppBook.dto.PaymentDTO;

import java.util.List;

public interface PaymentService {
    PaymentDTO makePayment(PaymentDTO paymentDTO);

    List<PaymentDTO> getPaymentsByUserId(Long userId);

    PaymentDTO getPaymentById(Long paymentId);
}