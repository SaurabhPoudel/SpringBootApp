package com.example.firstAppBook.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.*;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BookDTO {
    private Long id;
    @NotBlank(message= "Title cannot be blank")
    private String title;

    @Pattern(regexp = "\\d{13}",message = "ISBN must be a 13-digit number")
    private String isbn;
    private PublisherDTO publisher;
    private Set<AuthorDTO> authors;
    private int publicationYear;
}
