package com.example.firstAppBook.dto;

import com.example.firstAppBook.entity.OrderStatus;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class OrderDTO {
    private Long id;
    private LocalDateTime createdAt;
    private Double totalAmount;
    private OrderStatus status;
    private List<OrderItemDTO> items;
}
