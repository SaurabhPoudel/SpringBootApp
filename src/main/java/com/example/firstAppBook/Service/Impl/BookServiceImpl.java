package com.example.firstAppBook.Service.Impl;

import com.example.firstAppBook.DTO.BookDTO;
import com.example.firstAppBook.DTO.BookMapper;
import com.example.firstAppBook.Repository.BookRepository;
import com.example.firstAppBook.Entity.Book;
import com.example.firstAppBook.Service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookServiceImpl implements BookService { @Autowired

@Autowired
private final BookRepository bookRepository;

    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public List<BookDTO> getAllBooks() {

        return bookRepository.findAll().stream()
                .map(BookMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public BookDTO getBookById(Long id)
    {
        return bookRepository.findById(id)
                .map(BookMapper::toDTO)
                .orElse(null);
    }

    @Override
    public BookDTO saveBook(BookDTO bookDTO) {
        Book book = BookMapper.toEntity(bookDTO);
        Book savedBook = bookRepository.save(book);
        return BookMappper.toDTO(savedBook);
    }
    @Override
    public BookDTO updateBook(Long id, Book bookDetails) {
        return bookRepository.findById(id).map(book -> {
            book.setTitle(bookDetails.getTitle());
            book.setAuthor(bookDetails.getAuthor());
            book.setIsbn(bookDetails.getIsbn());
            return bookRepository.save(book);
        }).orElse(null);
    }

    public void deleteBook(Long id) {
        bookRepository.deleteById(id);
    }
    @Override
    public List<BookDTO> findByAuthorName(String authorName) {
        return bookRepository.findByAuthorName(authorName).stream()
                .map(BookDTO::toDTO)
                .collect(Collectors.toList());
    }
    @Override
    public List<BookDTO> findByPublisherName(String publisherName) {
        return bookRepository.findByPublisherName(publisherName).stream()
                .map(BookDTO::toDTO)
                .collect(Collectors.toList());

    }
}
