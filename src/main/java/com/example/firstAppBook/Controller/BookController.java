package com.example.firstAppBook.Controller;

import com.example.firstAppBook.Entity.Book;
import com.example.firstAppBook.Repository.BookRepository;
import com.example.firstAppBook.Service.BookService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/books")
@CrossOrigin(origins = "*")
public class BookController {
    // This class will handle HTTP requests related to books
    // You can define methods here to handle GET, POST, PUT, DELETE requests
    // For example:

    // @GetMapping
    // public List<Book> getAllBooks() {
    //     return bookService.getAllBooks();
    // }

    // @PostMapping
    // public Book createBook(@RequestBody Book book) {
    //     return bookService.createBook(book);
    // }

    // Add more methods as needed for your application
    private final BookService bookService;
    private final BookRepository bookRepository;

    public BookController(BookService bookService, BookRepository bookRepository) {
        this.bookService = bookService;
        this.bookRepository = bookRepository;
    }
    // You can inject the BookService and BookRepository here for use in your methods
    // Example method to get all books
    // public List<Book> getAllBooks() {
    //     return bookService.getAllBooks();
    // }
    // Example method to create a new book
    // public Book createBook(@RequestBody Book book) {
    //     return bookService.createBook(book);
    // }
    @GetMapping
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
        // You can implement the logic to retrieve all books from the repository here
        // return bookRepository.findAll();
    }
    @GetMapping("/{id}")
    public ResponseEntity<Book> getBookById(@PathVariable String id) {
        return bookRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    @PostMapping
    public Book createBook(@RequestBody Book book) {
        return bookService.apply(book);
    }

  /*  @PutMapping("/{id}")
    public ResponseEntity<Book> updateBook(@PathVariable String id, @RequestBody Book book) {
        return bookRepository.findById(id)
                .map(existingBook -> {
                    book.setId(Long.valueOf(id));
                    return ResponseEntity.ok(bookService.apply(book));
                })
                .orElse(ResponseEntity.notFound().build());
    }*/

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable String id) {
        if (bookRepository.findById(id).isPresent()) {
            bookRepository.deleteById(id);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
}
