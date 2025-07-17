package com.example.firstAppBook.dto;

import com.example.firstAppBook.entity.Author;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@NoArgsConstructor
public class AuthorMapper {

    public AuthorDTO toDTO(Author author) {
        if (author == null) return null;
        return AuthorDTO.builder()
                .id(author.getId())
                .name(author.getName())
                .build();
    }

    public Author toEntity(AuthorDTO dto) {
        if (dto == null) return null;
        Author author = new Author();
        author.setId(dto.getId());
        author.setName(dto.getName());
        return author;
    }
}
