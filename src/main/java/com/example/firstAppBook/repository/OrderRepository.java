package com.example.firstAppBook.repository;

import com.example.firstAppBook.entity.Order;
import com.example.firstAppBook.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findByUser(User user);
}
