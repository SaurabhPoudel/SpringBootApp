package com.example.firstAppBook.Repository;

import com.example.firstAppBook.Entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    @Query("SELECT b FROM Book b WHERE b.author = :name")
    List<Book> findByAuthorName(@Param("name") String name);
    // Additional query methods can be defined here if needed
    // For example, to find books by title or ISBN:
    List<Book> findByTitleContaining(String title);
    List<Book> findByIsbn(String isbn);
    // You can also define custom queries using @Query annotation if needed
    @Query("SELECT b FROM Book b WHERE b.publisher.name = :publisherName")
    List<Book> findByPublisherName(@Param("publisherName")String publisherName);
}
