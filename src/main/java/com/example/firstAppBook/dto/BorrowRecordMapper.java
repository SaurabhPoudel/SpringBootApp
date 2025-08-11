package com.example.firstAppBook.dto;

import com.example.firstAppBook.entity.Book;
import com.example.firstAppBook.entity.BorrowRecord;
import com.example.firstAppBook.entity.User;
import org.springframework.stereotype.Component;

@Component
public class BorrowRecordMapper {
    public BorrowRecordDTO toDTO(BorrowRecord record) {
        return BorrowRecordDTO.builder()
                .id(record.getId())
                .userId(record.getUser().getId())
                .username(record.getUser().getUsername())
                .bookId(record.getBook().getId())
                .bookTitle(record.getBook().getTitle())
                .borrowDate(record.getBorrowDate())
                .returnDate(record.getReturnDate())
                .returned(record.isReturned())
                .build();
    }

    public BorrowRecord toEntity(BorrowRecordDTO dto, User user, Book book) {
        return BorrowRecord.builder()
                .id(dto.getId())
                .user(user)
                .book(book)
                .borrowDate(dto.getBorrowDate())
                .returnDate(dto.getReturnDate())
                .returned(dto.isReturned())
                .build();
    }
}
