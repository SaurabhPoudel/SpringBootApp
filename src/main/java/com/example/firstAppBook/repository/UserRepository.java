package com.example.firstAppBook.repository;

import com.example.firstAppBook.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    // Additional query methods can be defined here if needed
    // For example, to find a user by username:
    Optional<User> findByUsername(String username);
}
