package com.example.firstAppBook.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import org.springframework.data.annotation.Id;

@Entity
@Table(name = "books")
public class Book {

    @Id
    private Long id;
    private String title;
    private String author;
    private String isbn;
    private int publicationYear;

    // Getters and setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getAuthor() { return author; }
    public void setAuthor(String author) { this.author = author; }

    public int getPublicationYear() {return publicationYear;}
    public void setPublicationYear(int publicationYear) {this.publicationYear = publicationYear;}

    public String getIsbn() {return isbn;
    }
    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }
}