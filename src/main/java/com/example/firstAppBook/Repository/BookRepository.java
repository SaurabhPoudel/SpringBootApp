package com.example.firstAppBook.Repository;

import com.example.firstAppBook.Entity.Book;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface BookRepository extends MongoRepository<Book, String> {
}
