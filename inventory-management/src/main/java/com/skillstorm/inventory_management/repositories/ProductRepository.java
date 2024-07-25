package com.skillstorm.inventory_management.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skillstorm.inventory_management.models.Product;

public interface ProductRepository extends JpaRepository<Product, Integer>{

}
