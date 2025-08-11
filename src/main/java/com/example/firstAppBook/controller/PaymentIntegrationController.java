package com.example.firstAppBook.controller;

import com.example.firstAppBook.dto.PaymentDTO;
import com.example.firstAppBook.entity.Payment;
import com.example.firstAppBook.entity.PaymentStatus;
import com.example.firstAppBook.entity.PaymentType;
import com.example.firstAppBook.entity.User;
import com.example.firstAppBook.repository.PaymentRepository;
import com.example.firstAppBook.repository.UserRepository;
import com.example.firstAppBook.service.PaymentService;
import com.stripe.param.PaymentIntentCreateParams;
import com.stripe.model.Event;
import com.stripe.model.PaymentIntent;
import com.stripe.net.Webhook;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/payments")
@RequiredArgsConstructor
public class PaymentIntegrationController {

    private final com.stripe.StripeClient stripeClient;
    private final PaymentRepository paymentRepository;
    private final PaymentService paymentService;
    private final UserRepository userRepository;

    @Value("${stripe.webhook-secret}")
    private String webhookSecret;

    @Value("${stripe.currency:gbp}")
    private String currency;

    private User currentUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return userRepository.findByUsername(auth.getName()).orElseThrow();
    }

    @GetMapping("/me")
    public ResponseEntity<List<PaymentDTO>> myPayments(){
        User user = currentUser();
        List<PaymentDTO> list = paymentService.getPaymentsByUserId(user.getId());
        return ResponseEntity.ok(list);
    }

    public record CreateIntentRequest(Long amount, String type) {}

    @PostMapping("/intent")
    public ResponseEntity<Map<String, String>> createIntent(@RequestBody CreateIntentRequest req){
        User user = currentUser();
        PaymentIntentCreateParams params = PaymentIntentCreateParams.builder()
                .setAmount(req.amount()) // minor units
                .setCurrency(currency)
                .putMetadata("userId", String.valueOf(user.getId()))
                .putMetadata("type", req.type())
                .build();
        try {
            PaymentIntent pi = stripeClient.paymentIntents().create(params);
            return ResponseEntity.ok(Map.of("clientSecret", pi.getClientSecret()));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Map.of("message", e.getMessage()));
        }
    }

    @PostMapping("/webhook")
    public ResponseEntity<String> webhook(@RequestHeader("Stripe-Signature") String sigHeader, @RequestBody String payload){
        try {
            Event event = Webhook.constructEvent(payload, sigHeader, webhookSecret);
            if ("payment_intent.succeeded".equals(event.getType())) {
                PaymentIntent pi = (PaymentIntent) event.getData().getObject();
                Long userId = Long.valueOf(pi.getMetadata().get("userId"));
                String type = pi.getMetadata().get("type");
                User user = userRepository.findById(userId).orElse(null);
                if (user != null) {
                    Payment payment = Payment.builder()
                            .user(user)
                            .amount(pi.getAmount() / 100.0)
                            .paymentDate(LocalDateTime.now())
                            .paymentStatus(PaymentStatus.SUCCEEDED)
                            .paymentType("PURCHASE".equalsIgnoreCase(type) ? PaymentType.PURCHASE : PaymentType.FINE)
                            .build();
                    paymentRepository.save(payment);
                }
            }
            return ResponseEntity.ok("ok");
        } catch (Exception e) {
            return ResponseEntity.status(400).body("webhook error: "+e.getMessage());
        }
    }
}
