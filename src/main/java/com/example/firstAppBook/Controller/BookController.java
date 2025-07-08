package com.example.firstAppBook.Controller;

import com.example.firstAppBook.DTO.BookDTO;
import com.example.firstAppBook.Service.BookService;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/books")
@CrossOrigin(origins = "*")
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

//ResponseENtity is not used in this controller, so it can be removed
    @GetMapping
    public ResponseEntity<List<BookDTO>> getAllBooks()  {
        List<BookDTO> books = bookService.getAllBooks();
        if (books.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(books, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookDTO> getBookById(@PathVariable Long id){
        BookDTO book = bookService.getBookById(id);
        if (book != null) {
            return new ResponseEntity<>(book, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/add")
    public ResponseEntity<BookDTO> addBook(@RequestBody BookDTO book){
        BookDTO savedBook = bookService.saveBook(book);
        return  new ResponseEntity<>(savedBook, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<BookDTO> updateBook(@PathVariable Long id, @RequestBody BookDTO book) {
        BookDTO updatedBook = bookService.updateBook(id, book);
        if(updatedBook != null) {
            return new ResponseEntity<>(updatedBook, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable Long id) {
        bookService.deleteBook(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/search")
    public ResponseEntity<List<BookDTO>> searchBooksByAuthor(@RequestParam String authorName) {
        List<BookDTO> books = bookService.findByAuthorName(authorName);
        return new ResponseEntity<>(books, HttpStatus.OK);
    }

    @GetMapping("/search-by-publisher")
    public ResponseEntity<List<BookDTO>> searchBooksByPublisher(@RequestParam String publisherName) {
        List<BookDTO> books = bookService.findByPublisherName(publisherName);
        return new ResponseEntity<>(books, HttpStatus.OK);
    }
}
