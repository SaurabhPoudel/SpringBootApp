package com.example.firstAppBook.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class StripeConfig {
    @Bean
    public com.stripe.StripeClient stripeClient(@Value("${stripe.secret-key}") String secretKey) {
        return new com.stripe.StripeClient(secretKey);
    }
}
