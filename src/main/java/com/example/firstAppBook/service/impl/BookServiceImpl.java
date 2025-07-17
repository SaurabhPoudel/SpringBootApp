package com.example.firstAppBook.service.impl;
import com.example.firstAppBook.dto.BookDTO;
import com.example.firstAppBook.dto.BookMapper;
import com.example.firstAppBook.repository.BookRepository;
import com.example.firstAppBook.service.BookService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;
import com.example.firstAppBook.entity.Book;
import java.util.Optional;

@Service
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    @Autowired
    private final BookMapper bookMapper;
    private static final Logger logger = LoggerFactory.getLogger(BookServiceImpl.class);


    public BookServiceImpl(BookRepository bookRepository, BookMapper bookMapper) {
        this.bookRepository = bookRepository;
        this.bookMapper = bookMapper;
    }

    @Override
    @Transactional(readOnly = true)
    public List<BookDTO> getAllBooks() {
        logger.info("Fetching all books from the repository");
        return bookRepository.findAll()
                .stream()
                .map(bookMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public BookDTO getBookById(Long id)
    {
        Book book = bookRepository.findById(id).orElse(null);
        return bookMapper.toDTO(book);
        //ask here why we cant use bookMapper
    }

    @Override
    @Transactional
    public BookDTO saveBook(BookDTO bookDTO) {
        Book book = bookMapper.toEntity(bookDTO);
        Book savedBook = bookRepository.save(book);
        return bookMapper.toDTO(savedBook);
    }
    @Override
    public BookDTO updateBook(Long id, BookDTO bookDTO)
    {
        Book existingBook = bookRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Book not found with ID: " + id));

        // Update values
        existingBook.setTitle(bookDTO.getTitle());
        if (bookDTO.getPublisher() != null) {
            //existingBook.getPublisher().setId(bookDTO.getPublisher().getId());
            existingBook.getPublisher().setName(bookDTO.getPublisher().getName());
        }

        Book updatedBook = bookRepository.save(existingBook);
        return bookMapper.toDTO(updatedBook);
    }

    @Override
    public void deleteBook(Long id) {
        bookRepository.deleteById(id);
    }

    @Override
    public Optional<BookDTO> findByPublisherName(String publisherName) {
        return bookRepository.findByPublisherName(publisherName).stream()
                .filter(e ->e.getPublisher().getName().equals(publisherName))
                .findFirst()
                .map(bookMapper::toDTO);
        //ask question here why we cant use bookMapper


    }
    // Stream< Employee> a = Stream.filter();
}
