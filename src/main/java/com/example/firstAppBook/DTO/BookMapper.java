package com.example.firstAppBook.DTO;

import com.example.firstAppBook.Entity.Author;
import com.example.firstAppBook.Entity.Book;
import com.example.firstAppBook.Entity.Publisher;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class BookMapper {

    public static BookDTO toDTO(Book book) {
        if (book == null) {
            return null;
        }
        List<AuthorDTO> authors = book.getAuthors() != null
                ? book.getAuthors().stream()
                .map(a -> new AuthorDTO(a.getId(), a.getName(), a.getBooks()))
                .collect(Collectors.toList())
                : null;

        Publisher publisher = book.getPublisher();
        PublisherDTO publisherDTO = (publisher != null)
                ? new PublisherDTO(publisher.getId(), publisher.getName())
                : null;

        return new BookDTO(
                book.getId(),
                book.getTitle(),
                book.getIsbn(),
                publisherDTO,
                authors,
                book.getPublicationYear()
        );
    }

    public static Book toEntity(BookDTO bookDTO) {
        if (bookDTO == null) {
            return null;
        }
        Book book = new Book();
        book.setId(bookDTO.getId());
        book.setTitle(bookDTO.getTitle());
        book.setIsbn(bookDTO.getIsbn());
        book.setPublicationYear(bookDTO.getPublicationYear());

        if (bookDTO.getPublisher() != null) {
            PublisherDTO pubDTO = bookDTO.getPublisher();
            Publisher publisher = new Publisher();
            publisher.setId(pubDTO.getId());
            publisher.setName(pubDTO.getName());
            book.setPublisher(publisher);
        }

        if (bookDTO.getAuthors() != null) {
            Set<Author> authors = bookDTO.getAuthors().stream()
                    .map(a -> {
                        Author author = new Author();
                        author.setId(a.getId());
                        author.setName(a.getName());
                        return author;
                    })
                    .collect(Collectors.toSet());
            book.setAuthors(authors);
        }
        return book;
    }
}



