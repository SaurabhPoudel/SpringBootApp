package com.example.firstAppBook.repository;

import com.example.firstAppBook.entity.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {
// No additional methods are needed for basic CRUD operations}
    // JpaRepository provides all necessary methods like save, findById, findAll, deleteById, etc.

    // If you need custom queries, you can define them here
    // For example:
     List<Author> findByName(String name);
}