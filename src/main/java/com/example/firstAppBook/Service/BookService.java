package com.example.firstAppBook.Service;

import com.example.firstAppBook.Entity.Book;

import java.util.List;
import java.util.function.Function;

@FunctionalInterface
public interface BookService extends Function<Book, Book> {

    /**
     * Saves a book entity.
     *
     * @param book the book entity to save
     * @return the saved book entity
     */
    @Override
    Book apply(Book book);

    /**
     * Default method to save a book entity.
     *
     * @param book the book entity to save
     * @return the saved book entity
     */
    default List<Book>getAllBooks(){
        throw new UnsupportedOperationException("getAllBooks() is not implemented in functional interface");
    }
}
