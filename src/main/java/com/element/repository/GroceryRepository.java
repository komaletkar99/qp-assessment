package com.element.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.element.entity.Grocery;
@Repository
public interface GroceryRepository extends JpaRepository<Grocery, Long>{

}
