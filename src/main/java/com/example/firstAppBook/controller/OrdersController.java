package com.example.firstAppBook.controller;

import com.example.firstAppBook.dto.OrderDTO;
import com.example.firstAppBook.service.OrderService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
public class OrdersController {

    private final OrderService orderService;

    @PostMapping
    public ResponseEntity<Map<String, Object>> create(@RequestBody CreateOrderRequest req){
        OrderDTO dto = orderService.createForCurrentUser(req.getItems());
        return ResponseEntity.ok(Map.of("orderId", dto.getId()));
    }

    @GetMapping("/me")
    public ResponseEntity<List<OrderDTO>> myOrders(){
        return ResponseEntity.ok(orderService.listMine());
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderDTO> get(@PathVariable Long id){
        return ResponseEntity.ok(orderService.getMine(id));
    }

    @Data
    public static class CreateOrderRequest {
        private List<OrderService.ItemRequest> items;
    }
}
