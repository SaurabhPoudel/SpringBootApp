package com.example.firstAppBook.service;

import com.example.firstAppBook.dto.BookDTO;
import com.example.firstAppBook.dto.BookMapper;
import com.example.firstAppBook.dto.PublisherDTO;
import com.example.firstAppBook.entity.Book;
import com.example.firstAppBook.entity.Publisher;
import com.example.firstAppBook.repository.BookRepository;
import com.example.firstAppBook.service.impl.BookServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class BookServiceImplTest {
    @Mock
    private BookRepository bookRepository;
    @Mock
    private BookMapper bookMapper;
    @InjectMocks
    private BookServiceImpl bookService;

    private Book book;
    private BookDTO bookDTO;

    /*@BeforeEach
    public void setUp() {
        bookRepository = Mockito.mock(BookRepository.class);
        bookService=new BookServiceImpl(bookRepository);

    }
    @Test
    public void testGetBook{}*/
    // Implement the test logic here
    // For example, you can use Mockito to define the behavior of bookRepository
    // and then call the method you want to test in bookService.}
    @BeforeEach
    void setUp() {
        Publisher publisher = new Publisher();
        publisher.setId(1L);
        publisher.setName("O'Reilly");

        book = new Book();
        book.setId(1L);
        book.setTitle("Java Basics");
        book.setPublisher(publisher);

        bookDTO = new BookDTO();
        bookDTO.setId(1L);
        bookDTO.setTitle("Java Basics");
        bookDTO.setPublisher(new PublisherDTO(1L, "O'Reilly"));
    }

    @Test
    void getAllBooks_shouldReturnListOfBookDTOs() {
        when(bookRepository.findAll()).thenReturn(Collections.singletonList(book));
        when(bookMapper.toDTO(book)).thenReturn(bookDTO);

        List<BookDTO> result = bookService.getAllBooks();

        assertEquals(1, result.size());
        assertEquals("Java Basics", result.get(0).getTitle());
        verify(bookRepository).findAll();
        verify(bookMapper).toDTO(book);
    }

    @Test
    void getBookById_shouldReturnBookDTO_whenBookExists() {
        when(bookRepository.findById(1L)).thenReturn(Optional.of(book));
        when(bookMapper.toDTO(book)).thenReturn(bookDTO);

        BookDTO result = bookService.getBookById(1L);

        assertNotNull(result);
        assertEquals("Java Basics", result.getTitle());
    }

    @Test
    void getBookById_shouldReturnNull_whenBookNotFound() {
        when(bookRepository.findById(99L)).thenReturn(Optional.empty());

        BookDTO result = bookService.getBookById(99L);

        assertNull(result);
    }

    @Test
    void saveBook_shouldSaveAndReturnBookDTO() {
        when(bookMapper.toEntity(bookDTO)).thenReturn(book);
        when(bookRepository.save(book)).thenReturn(book);
        when(bookMapper.toDTO(book)).thenReturn(bookDTO);

        BookDTO savedBook = bookService.saveBook(bookDTO);

        assertNotNull(savedBook);
        assertEquals("Java Basics", savedBook.getTitle());
        verify(bookRepository).save(book);
    }

    @Test
    void updateBook_shouldUpdateAndReturnUpdatedBookDTO() {
        when(bookRepository.findById(1L)).thenReturn(Optional.of(book));
        when(bookRepository.save(book)).thenReturn(book);
        when(bookMapper.toDTO(book)).thenReturn(bookDTO);

        BookDTO result = bookService.updateBook(1L, bookDTO);

        assertNotNull(result);
        assertEquals("Java Basics", result.getTitle());
    }

    @Test
    void updateBook_shouldThrowException_whenBookNotFound() {
        when(bookRepository.findById(2L)).thenReturn(Optional.empty());

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            bookService.updateBook(2L, bookDTO);
        });

        assertEquals("Book not found with ID: 2", exception.getMessage());
    }

    @Test
    void deleteBook_shouldCallRepositoryDeleteById() {
        bookService.deleteBook(1L);
        verify(bookRepository).deleteById(1L);
    }

    @Test
    void findByPublisherName_shouldReturnBookDTOIfPublisherMatches() {
        when(bookRepository.findByPublisherName("O'Reilly")).thenReturn(List.of(book));
        when(bookMapper.toDTO(book)).thenReturn(bookDTO);

        Optional<BookDTO> result = bookService.findByPublisherName("O'Reilly");

        assertTrue(result.isPresent());
        assertEquals("Java Basics", result.get().getTitle());
    }

    @Test
    void findByPublisherName_shouldReturnEmptyIfNoMatch() {
        when(bookRepository.findByPublisherName("Penguin")).thenReturn(Collections.emptyList());

        Optional<BookDTO> result = bookService.findByPublisherName("Penguin");

        assertTrue(result.isEmpty());
    }
}
