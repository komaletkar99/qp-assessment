package com.element.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.element.entity.Grocery;
import com.element.service.GroseryService;

@RestController
@RequestMapping("/api/admin/groceries")
public class MainController {

	    @Autowired
	    private GroseryService groceryService;

	    @PostMapping
	    public ResponseEntity<Grocery> addGrocery(@RequestBody Grocery grocery) {
	        return ResponseEntity.ok(groceryService.addGrocery(grocery));
	    }

	    @GetMapping
	    public ResponseEntity<List<Grocery>> getAllGroceries() {
	        return ResponseEntity.ok(groceryService.getAllGroceries());
	    }

	    @PutMapping("/{id}")
	    public ResponseEntity<Grocery> updateGrocery(@PathVariable Long id, @RequestBody Grocery grocery) {
	        return ResponseEntity.ok(groceryService.updateGrocery(id, grocery));
	    }

	    @DeleteMapping("/{id}")
	    public ResponseEntity<Void> deleteGrocery(@PathVariable Long id) {
	        groceryService.deleteGrocery(id);
	        return ResponseEntity.noContent().build();
	    }

	    @PatchMapping("/{id}/inventory")
	    public ResponseEntity<Void> updateInventory(@PathVariable Long id, @RequestBody Map<String, Integer> request) {
	        groceryService.updateInventory(id, request.get("inventory"));
	        return ResponseEntity.ok().build();
	    }
	}


