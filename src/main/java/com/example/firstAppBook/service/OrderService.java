package com.example.firstAppBook.service;

import com.example.firstAppBook.dto.OrderDTO;

import java.util.List;

public interface OrderService {
    OrderDTO createForCurrentUser(List<ItemRequest> items);
    List<OrderDTO> listMine();
    OrderDTO getMine(Long id);

    class ItemRequest {
        public Long bookId;
        public Integer quantity;
    }
}
