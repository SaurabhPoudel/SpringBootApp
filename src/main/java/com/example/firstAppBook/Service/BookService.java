package com.example.firstAppBook.Service;

import com.example.firstAppBook.DTO.BookDTO;
import com.example.firstAppBook.Entity.Book;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.function.Function;


public interface BookService{
    BookDTO saveBook(BookDTO book);
    List<BookDTO> getAllBooks();
    BookDTO getBookById(Long id);
    BookDTO updateBook(Long id, BookDTO book);
    void deleteBook(Long id);
    List<BookDTO> findByAuthorName(String authorName);
    List<BookDTO> findByPublisherName(String publisherName);
}
