package com.example.firstAppBook.service;

import com.example.firstAppBook.dto.BookDTO;
import java.util.List;
import java.util.Optional;

public interface BookService{
    BookDTO saveBook(BookDTO book);
    List<BookDTO> getAllBooks();
    BookDTO getBookById(Long id);
    BookDTO updateBook(Long id, BookDTO book);

   // BookDTO updateBook(Long id, Book bookDetails);

    void deleteBook(Long id);

    Optional<BookDTO> findByPublisherName(String publisherName);

}
