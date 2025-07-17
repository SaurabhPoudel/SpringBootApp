package com.example.firstAppBook.dto;

import com.example.firstAppBook.entity.Book;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class BookMapper {

    private final PublisherMapper publisherMapper;
    private final AuthorMapper authorMapper;

    public BookDTO toDTO(Book book) {
        if (book == null) return null;
        return BookDTO.builder()
                .id(book.getId())
                .title(book.getTitle())
                .isbn(book.getIsbn())
                .publicationYear(book.getPublicationYear())
                .publisher(publisherMapper.toDTO(book.getPublisher()))
                .authors(book.getAuthors() != null
                        ? book.getAuthors().stream()
                        .map(authorMapper::toDTO)
                        .collect(Collectors.toSet())
                        : null)
                .build();
    }

    public Book toEntity(BookDTO dto) {
        if (dto == null) return null;
        Book book = new Book();
        book.setId(dto.getId());
        book.setTitle(dto.getTitle());
        book.setIsbn(dto.getIsbn());
        book.setPublicationYear(dto.getPublicationYear());
        book.setPublisher(publisherMapper.toEntity(dto.getPublisher()));

        if (dto.getAuthors() != null) {
            book.setAuthors(dto.getAuthors().stream()
                    .map(authorMapper::toEntity)
                    .collect(Collectors.toSet()));
        }
        return book;
    }
}
