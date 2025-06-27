package com.example.firstAppBook.Repository;
import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import com.example.firstAppBook.Entity.Book;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

@DataMongoTest
public class BookRepositoryTest {
    @Autowired
    private BookRepository bookRepository;

    @Test
    void testSaveAndFindBook() {
        Book book = new Book();
        book.setId("1");
        book.setTitle("JUnit in Action");
        book.setAuthor("Smith");

        bookRepository.save(book);

        Optional<Book> found = bookRepository.findById("1");
        assertThat(found).isPresent();
        assertThat(found.get().getTitle()).isEqualTo("JUnit in Action");
    }
}
