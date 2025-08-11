package com.example.firstAppBook.service.impl;

import com.example.firstAppBook.dto.BorrowRecordDTO;
import com.example.firstAppBook.entity.Book;
import com.example.firstAppBook.entity.BorrowRecord;
import com.example.firstAppBook.entity.Payment;
import com.example.firstAppBook.entity.User;
import com.example.firstAppBook.exception.BadRequestException;
import com.example.firstAppBook.exception.ResourceNotFoundException;
import com.example.firstAppBook.repository.BookRepository;
import com.example.firstAppBook.repository.BorrowRecordRepository;
import com.example.firstAppBook.repository.PaymentRepository;
import com.example.firstAppBook.repository.UserRepository;
import com.example.firstAppBook.service.BorrowService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BorrowServiceImpl implements BorrowService {
    private final BorrowRecordRepository borrowRecordRepository;
    private final BookRepository bookRepository;
    private final UserRepository userRepository;
    private final PaymentRepository paymentRepository;

    @Autowired
    public BorrowServiceImpl(BorrowRecordRepository borrowRecordRepository,
                             BookRepository bookRepository,
                             UserRepository userRepository
    , PaymentRepository paymentRepository) {
        this.borrowRecordRepository = borrowRecordRepository;
        this.bookRepository = bookRepository;
        this.userRepository = userRepository;
        this.paymentRepository = paymentRepository;
    }

    @Override
    public BorrowRecordDTO borrowBook(Long userId, Long bookId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id " + userId));
        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new ResourceNotFoundException("Book not found with id " + bookId));

        if (!book.isAvailable()) {
            throw new BadRequestException("Book is currently not available");
        }

        // Check if user already borrowed the book and hasn't returned it
        borrowRecordRepository.findByUserAndBookAndReturnedFalse(user, book).ifPresent(b -> {
            throw new BadRequestException("Book is already borrowed by user and not returned yet");
        });

        BorrowRecord record = new BorrowRecord();
        record.setUser(user);
        record.setBook(book);
        record.setBorrowDate(LocalDateTime.now());
        record.setReturned(false);

        // Update book availability
        book.setAvailable(false);
        bookRepository.save(book);

        return mapToDTO(borrowRecordRepository.save(record));
    }
    @Override
    @Transactional
    public BorrowRecordDTO returnBook(Long userId, Long bookId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new ResourceNotFoundException("Book not found"));

        BorrowRecord record = borrowRecordRepository
                .findByUserAndBookAndReturnedFalse(user, book)
                .orElseThrow(() -> new ResourceNotFoundException("Active borrow record not found"));

        record.setReturned(true);
        record.setReturnDate(LocalDateTime.now());
        book.setAvailable(true);

        // Overdue calculation — assuming borrow duration = 14 days
        LocalDateTime dueDate = record.getBorrowDate().plusDays(14);
        long overdueDays = java.time.temporal.ChronoUnit.DAYS.between(dueDate, record.getReturnDate());


        if (overdueDays > 0) {
            double fee = overdueDays * 1.0; // £1 per overdue day
            Payment payment = Payment.builder()
                    .amount(fee)
                    .paymentDate(LocalDateTime.now())
                    .description("Overdue fee for book: " + book.getTitle())
                    .user(user)
                    .build();
            paymentRepository.save(payment);
        }

        return mapToDTO(borrowRecordRepository.save(record));
    }
   /* @Override
    public BorrowRecordDTO returnBook(Long userId, Long bookId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new ResourceNotFoundException("Book not found"));

        BorrowRecord record = borrowRecordRepository.findByUserAndBookAndReturnedFalse(user, book)
                .orElseThrow(() -> new BadRequestException("This book was not borrowed or already returned"));

        record.setReturned(true);
        record.setReturnDate(LocalDate.now());

        book.setAvailable(true);
        bookRepository.save(book);

        return mapToDTO(borrowRecordRepository.save(record));
    }*/

    @Override
    public List<BorrowRecordDTO> getUserBorrowHistory(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        return borrowRecordRepository.findByUser(user)
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    private BorrowRecordDTO mapToDTO(BorrowRecord record) {
        BorrowRecordDTO dto = new BorrowRecordDTO();
        dto.setId(record.getId());
        dto.setBookId(record.getBook().getId());
        dto.setUserId(record.getUser().getId());
        dto.setBorrowDate(record.getBorrowDate());
        dto.setReturnDate(record.getReturnDate());
        dto.setReturned(record.isReturned());
        return dto;
    }
}

