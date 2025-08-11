package com.example.firstAppBook.controller;

import com.example.firstAppBook.dto.AuthorDTO;
import com.example.firstAppBook.dto.AuthorMapper;
import com.example.firstAppBook.repository.AuthorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/authors")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class AuthorsController {
    private final AuthorRepository authorRepository;
    private final AuthorMapper authorMapper;

    @GetMapping
    public List<AuthorDTO> list() {
        return authorRepository.findAll().stream()
                .map(authorMapper::toDTO)
                .collect(Collectors.toList());
    }
}
