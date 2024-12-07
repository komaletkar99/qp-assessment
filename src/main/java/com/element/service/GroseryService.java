package com.element.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.element.entity.Grocery;
import com.element.repository.GroceryRepository;

@Service
public class GroseryService {
    @Autowired
    private GroceryRepository groceryRepository;

    public Grocery addGrocery(Grocery grocery) {
        return groceryRepository.save(grocery);
    }

    public List<Grocery> getAllGroceries() {
        return groceryRepository.findAll();
    }

    public Grocery updateGrocery(Long id, Grocery grocery) {
        Grocery existing = groceryRepository.findById(id).orElseThrow(() -> new RuntimeException("Grocery not found"));
        existing.setName(grocery.getName());
        existing.setPrice(grocery.getPrice());
        existing.setInventory(grocery.getInventory());
        return groceryRepository.save(existing);
    }

    public void deleteGrocery(Long id) {
        groceryRepository.deleteById(id);
    }

    public void updateInventory(Long id, Integer inventory) {
        Grocery grocery = groceryRepository.findById(id).orElseThrow(() -> new RuntimeException("Grocery not found"));
        grocery.setInventory(inventory);
        groceryRepository.save(grocery);
    }
}
