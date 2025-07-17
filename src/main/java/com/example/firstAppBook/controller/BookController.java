package com.example.firstAppBook.controller;

import com.example.firstAppBook.dto.BookDTO;
import com.example.firstAppBook.service.BookService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/books")
@CrossOrigin(origins = "*")
public class BookController {
// The BookController class is a REST controller that handles HTTP requests related to books.
// It provides endpoints for creating, reading, updating, and deleting books.

    private final BookService bookService;
    private static final Logger logger = LoggerFactory.getLogger(BookController.class);

    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

//ResponseENtity is not used in this controller, so it can be removed
    @GetMapping
    public ResponseEntity<List<BookDTO>> getAllBooks()  {
        logger.info("getting all books");
        List<BookDTO> books = bookService.getAllBooks();
        if (books.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(books, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookDTO> getBookById(@PathVariable Long id){
        logger.info("Fetching book with ID: {}", id);
        BookDTO book = bookService.getBookById(id);
        if (book != null) {
            return new ResponseEntity<>(book, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/add")
    public ResponseEntity<BookDTO> addBook(@RequestBody BookDTO book){
        logger.info("Adding new book: {}", book.getTitle());
        BookDTO savedBook = bookService.saveBook(book);
        return  new ResponseEntity<>(savedBook, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<BookDTO> updateBook(@PathVariable Long id, @RequestBody BookDTO book) {
        logger.info("Updating book with ID: {}", id);
        BookDTO updatedBook = bookService.updateBook(id, book);
        if(updatedBook != null) {
            return new ResponseEntity<>(updatedBook, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable Long id) {
        logger.info("Deleting book with ID: {}", id);
        bookService.deleteBook(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/search-by-publisher")
    public ResponseEntity<BookDTO> searchBooksByPublisher(@RequestParam String publisherName) {
        logger.info("Searching for books by publisher: {}", publisherName);
        return bookService.findByPublisherName(publisherName)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
