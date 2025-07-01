package com.example.firstAppBook.Service;

import com.example.firstAppBook.Entity.Book;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.function.Function;


public interface BookService{
    Book saveBook(Book book);
    List<Book> getAllBooks();
    Book getBookById(Long id);
    Book updateBook(Long id, Book book);
    void deleteBook(Long id);
}
