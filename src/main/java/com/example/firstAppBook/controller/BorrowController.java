package com.example.firstAppBook.controller;

import com.example.firstAppBook.dto.BorrowRecordDTO;
import com.example.firstAppBook.entity.User;
import com.example.firstAppBook.repository.BookRepository;
import com.example.firstAppBook.repository.UserRepository;
import com.example.firstAppBook.service.BorrowService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class BorrowController {

    private final BorrowService borrowService;
    private final UserRepository userRepository;
    private final BookRepository bookRepository;

    private User currentUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        return userRepository.findByUsername(username).orElseThrow();
    }

    private BorrowRecordDTO enrich(BorrowRecordDTO dto) {
        if (dto == null) return null;
        userRepository.findById(dto.getUserId()).ifPresent(u -> dto.setUsername(u.getUsername()));
        bookRepository.findById(dto.getBookId()).ifPresent(b -> dto.setBookTitle(b.getTitle()));
        return dto;
    }

    public record BorrowRequest(Long bookId) {}

    @PostMapping("/api/borrows")
    public ResponseEntity<BorrowRecordDTO> borrow(@RequestBody BorrowRequest req) {
        User user = currentUser();
        BorrowRecordDTO dto = borrowService.borrowBook(user.getId(), req.bookId());
        return ResponseEntity.ok(enrich(dto));
    }

    @PostMapping("/api/borrows/{bookId}/return")
    public ResponseEntity<BorrowRecordDTO> returnBook(@PathVariable Long bookId) {
        User user = currentUser();
        BorrowRecordDTO dto = borrowService.returnBook(user.getId(), bookId);
        return ResponseEntity.ok(enrich(dto));
    }

    @GetMapping("/api/users/me/borrows")
    public ResponseEntity<List<BorrowRecordDTO>> myBorrows() {
        User user = currentUser();
        List<BorrowRecordDTO> list = borrowService.getUserBorrowHistory(user.getId())
                .stream().map(this::enrich).collect(Collectors.toList());
        return ResponseEntity.ok(list);
    }
}
