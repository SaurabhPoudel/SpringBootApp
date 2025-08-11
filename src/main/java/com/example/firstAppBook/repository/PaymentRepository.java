package com.example.firstAppBook.repository;

import com.example.firstAppBook.entity.Payment;
import com.example.firstAppBook.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long> {
    List<Payment> findByUser(User user);
    List<Payment> findByUserId(Long userId);
    // Additional query methods can be defined here if needed

}
