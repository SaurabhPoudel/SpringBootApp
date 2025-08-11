package com.example.firstAppBook.repository;

import com.example.firstAppBook.entity.Book;
import com.example.firstAppBook.entity.BorrowRecord;
import com.example.firstAppBook.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BorrowRecordRepository extends JpaRepository<BorrowRecord, Long> {
    List<BorrowRecord> findByUser(User user);

    List<BorrowRecord> findByBook(Book book);

    Optional<BorrowRecord> findByUserAndBookAndReturnedFalse(User user, Book book);
}
