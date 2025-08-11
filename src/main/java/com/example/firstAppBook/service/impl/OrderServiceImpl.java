package com.example.firstAppBook.service.impl;

import com.example.firstAppBook.dto.OrderDTO;
import com.example.firstAppBook.dto.OrderItemDTO;
import com.example.firstAppBook.entity.*;
import com.example.firstAppBook.exception.ResourceNotFoundException;
import com.example.firstAppBook.repository.BookRepository;
import com.example.firstAppBook.repository.OrderRepository;
import com.example.firstAppBook.repository.UserRepository;
import com.example.firstAppBook.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final UserRepository userRepository;
    private final BookRepository bookRepository;

    private User currentUser(){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return userRepository.findByUsername(auth.getName()).orElseThrow(() -> new ResourceNotFoundException("User not found"));
    }

    @Override
    public OrderDTO createForCurrentUser(List<ItemRequest> items) {
        User user = currentUser();
        Order order = Order.builder()
                .user(user)
                .createdAt(LocalDateTime.now())
                .status(OrderStatus.NEW)
                .totalAmount(0.0)
                .items(new ArrayList<>())
                .build();

        double total = 0.0;
        for (ItemRequest it : items) {
            Book book = bookRepository.findById(it.bookId)
                    .orElseThrow(() -> new ResourceNotFoundException("Book not found: " + it.bookId));
            double unitPrice = 0.0; // If you add price to Book later, use it here
            int qty = Math.max(1, it.quantity == null ? 1 : it.quantity);

            OrderItem oi = OrderItem.builder()
                    .book(book)
                    .quantity(qty)
                    .unitPrice(unitPrice)
                    .build();
            order.addItem(oi);
            total += unitPrice * qty;
        }
        order.setTotalAmount(total);
        Order saved = orderRepository.save(order);
        return toDTO(saved);
    }

    @Override
    public List<OrderDTO> listMine() {
        User user = currentUser();
        return orderRepository.findByUser(user).stream().map(this::toDTO).collect(Collectors.toList());
    }

    @Override
    public OrderDTO getMine(Long id) {
        User user = currentUser();
        Order o = orderRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Order not found"));
        if (!o.getUser().getId().equals(user.getId())) throw new ResourceNotFoundException("Order not found");
        return toDTO(o);
    }

    private OrderDTO toDTO(Order o){
        return OrderDTO.builder()
                .id(o.getId())
                .createdAt(o.getCreatedAt())
                .totalAmount(o.getTotalAmount())
                .status(o.getStatus())
                .items(o.getItems().stream().map(oi -> OrderItemDTO.builder()
                        .bookId(oi.getBook().getId())
                        .bookTitle(oi.getBook().getTitle())
                        .quantity(oi.getQuantity())
                        .unitPrice(oi.getUnitPrice())
                        .build()).collect(Collectors.toList()))
                .build();
    }
}
