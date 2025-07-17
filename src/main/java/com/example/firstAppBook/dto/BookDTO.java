package com.example.firstAppBook.dto;

import lombok.*;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BookDTO {
    private Long id;
    private String title;
    private String isbn;
    private PublisherDTO publisher;
    private Set<AuthorDTO> authors;
    private int publicationYear;
}
