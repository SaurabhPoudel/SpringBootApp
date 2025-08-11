package com.example.firstAppBook.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BorrowRecordDTO {
    private Long id;
    private Long userId;
    private String username;
    private Long bookId;
    private String bookTitle;
    private LocalDateTime borrowDate;
    private LocalDateTime returnDate;
    private boolean returned;
}
