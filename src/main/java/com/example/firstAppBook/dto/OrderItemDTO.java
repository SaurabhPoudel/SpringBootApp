package com.example.firstAppBook.dto;

import lombok.*;

@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class OrderItemDTO {
    private Long bookId;
    private String bookTitle;
    private Integer quantity;
    private Double unitPrice;
}
