package com.example.firstAppBook.DTO;

import java.util.List;

public class BookDTO {
    private Long id;
    private String title;
    private String isbn;
    private PublisherDTO publisher;
    private List<AuthorDTO> authors;
    private int publicationYear;

    public BookDTO() {
    }

    public BookDTO(Long id, String title, String isbn, PublisherDTO publisher, List<AuthorDTO> authors, int publicationYear) {
        this.id = id;
        this.title = title;
        this.isbn = isbn;
        this.publisher = publisher;
        this.authors = authors;
        this.publicationYear = publicationYear;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public PublisherDTO getPublisher() {
        return publisher;
    }

    public void setPublisher(PublisherDTO publisher) {
        this.publisher = publisher;
    }

    public AuthorDTO getAuthor() {
        return author.;
    }

    public void setAuthor(AuthorDTO author) {
        this.author = author;
    }

    public int getPublicationYear() {
        return publicationYear;
    }

    public void setPublicationYear(int publicationYear) {
        this.publicationYear = publicationYear;
    }
    @Override
    public String toString() {
        return "BookDTO{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", isbn='" + isbn + '\'' +
                ", publisher=" + publisher +
                ", author=" + author +
                ", publicationYear=" + publicationYear +
                '}';
    }

}
// This DTO class is used to transfer book data between different layers of the application.
