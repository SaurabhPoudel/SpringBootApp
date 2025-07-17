package com.example.firstAppBook.repository;

import com.example.firstAppBook.entity.Publisher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PublisherRepository extends JpaRepository<Publisher, Long>{
    // Additional query methods can be defined here if needed
    // For example, to find a publisher by name:
    Optional<Publisher> findByName(String name);
}
