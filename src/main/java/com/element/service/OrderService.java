package com.element.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.element.entity.Grocery;
import com.element.entity.Order;
import com.element.entity.OrderItem;
import com.element.repository.GroceryRepository;
import com.element.repository.OrderRepository;

@Service
public class OrderService {
	    @Autowired
	    private GroceryRepository groceryRepository;

	    @Autowired
	    private OrderRepository orderRepository;

	    public Order placeOrder(Long userId, List<OrderItem> items) {
	        Order order = new Order();
	        order.setUserId(userId);

	        List<OrderItem> processedItems = new ArrayList<>();

	        for (OrderItem item : items) {
	            Grocery grocery = groceryRepository.findById(item.getGrocery().getId())
	                .orElseThrow(() -> new RuntimeException("Grocery not found with ID: " + item.getGrocery().getId()));

	            if (grocery.getInventory() < item.getQuantity()) {
	                throw new RuntimeException("Insufficient inventory for grocery ID: " + grocery.getId());
	            }

	            // Deduct inventory
	            grocery.setInventory(grocery.getInventory() - item.getQuantity());
	            groceryRepository.save(grocery);

	            // Add the processed item to the list
	            item.setGrocery(grocery);
	            processedItems.add(item);
	        }

	        order.setItems(processedItems);
	        order.setTotalPrice(calculateTotalPrice(processedItems));
	        return orderRepository.save(order);
	    }

	    private Double calculateTotalPrice(List<OrderItem> items) {
	        return items.stream()
	                    .mapToDouble(item -> item.getGrocery().getPrice() * item.getQuantity())
	                    .sum();
	    }
	}
