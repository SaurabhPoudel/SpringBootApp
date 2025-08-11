package com.example.firstAppBook.controller;

import com.example.firstAppBook.dto.PaymentDTO;
import com.example.firstAppBook.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/payments")
@RequiredArgsConstructor
public class PaymentController {

    private final PaymentService paymentService;

    @PostMapping
    public ResponseEntity<PaymentDTO> makePayment(@RequestBody PaymentDTO paymentDTO) {
        PaymentDTO createdPayment = paymentService.makePayment(paymentDTO);
        return ResponseEntity.ok(createdPayment);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<PaymentDTO>> getPaymentsByUser(@PathVariable Long userId) {
        List<PaymentDTO> payments = paymentService.getPaymentsByUserId(userId);
        return ResponseEntity.ok(payments);
    }

    @GetMapping("/{paymentId}")
    public ResponseEntity<PaymentDTO> getPaymentById(@PathVariable Long paymentId) {
        PaymentDTO paymentDTO = paymentService.getPaymentById(paymentId);
        return ResponseEntity.ok(paymentDTO);
    }
}