package com.example.firstAppBook.Repository;

import com.example.firstAppBook.Entity.Publisher;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PublisherRepository extends JpaRepository<Publisher, Long>{
    // Additional query methods can be defined here if needed
    // For example, to find a publisher by name:
    Optional<Publisher> findByName(String name);
}
