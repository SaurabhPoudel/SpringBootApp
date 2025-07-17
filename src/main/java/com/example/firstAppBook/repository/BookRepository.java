package com.example.firstAppBook.repository;

import com.example.firstAppBook.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    // Additional query methods can be defined here if needed
    // For example, to find books by publisher name:
    List<Book> findByPublisherName(String publisherName);
}
