package com.example.firstAppBook.repositoryTests;

import com.example.firstAppBook.Entity.Book;
import com.example.firstAppBook.Repository.BookRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataMongoTest
public class BookRepositoryTest {
    @Autowired
    private BookRepository bookRepository;

    @Test
    void testSaveAndFindBook() {
        Book book = new Book();
        book.setId(1L);
        book.setTitle("JUnit in Action");
        book.setAuthor("Smith");

        bookRepository.save(book);

        Optional<Book> found = bookRepository.findById("1");
        assertThat(found).isPresent();
        assertThat(found.get().getTitle()).isEqualTo("JUnit in Action");
    }
}
