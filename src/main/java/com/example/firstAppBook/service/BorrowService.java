package com.example.firstAppBook.service;

import com.example.firstAppBook.dto.BorrowRecordDTO;

import java.util.List;

public interface BorrowService {
    BorrowRecordDTO borrowBook(Long userId, Long bookId);
    BorrowRecordDTO returnBook(Long userId, Long bookId);
    List<BorrowRecordDTO> getUserBorrowHistory(Long userId);
}
