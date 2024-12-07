package com.element.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.element.entity.Grocery;
import com.element.entity.Order;
import com.element.entity.OrderItem;
import com.element.service.GroseryService;
import com.element.service.OrderService;

@RestController
@RequestMapping("/api")
public class UserController {
    @Autowired
    private GroseryService groceryService;

    @Autowired
    private OrderService orderService;

    @GetMapping("/groceries")
    public ResponseEntity<List<Grocery>> getAvailableGroceries() {
        return ResponseEntity.ok(groceryService.getAllGroceries());
    }
    @PostMapping("/orders")
    public ResponseEntity<Order> placeOrder(@RequestBody Order order) {
        for (OrderItem item : order.getItems()) {
            if (item.getGrocery() == null || item.getGrocery().getId() == null) {
                throw new RuntimeException("Grocery item or ID is missing in the request payload");
            }
        }
        return ResponseEntity.ok(orderService.placeOrder(order.getUserId(), order.getItems()));
    }

}

