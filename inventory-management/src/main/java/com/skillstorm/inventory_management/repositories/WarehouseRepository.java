package com.skillstorm.inventory_management.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skillstorm.inventory_management.models.Warehouse;

public interface WarehouseRepository extends JpaRepository<Warehouse, Integer>{

}
