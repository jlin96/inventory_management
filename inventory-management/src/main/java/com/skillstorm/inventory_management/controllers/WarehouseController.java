package com.skillstorm.inventory_management.controllers;

import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.skillstorm.inventory_management.models.Product;
import com.skillstorm.inventory_management.models.Warehouse;
import com.skillstorm.inventory_management.services.WarehouseService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/warehouses")
public class WarehouseController {

    private WarehouseService service;

    public WarehouseController(WarehouseService service) {
        this.service = service;
    }

    @GetMapping
    public Iterable<Warehouse> findAll() {
        return service.findAll();
    }

    @GetMapping("/getProducts/{id}")
    public Iterable<Product> findAllProduct(@PathVariable int id) {
        return service.findAllProduct(id);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Warehouse> findById(@PathVariable int id) {
        Optional<Warehouse> warehouse = service.findById(id);
        if(warehouse.isPresent()) {
            return ResponseEntity.ok(warehouse.get());
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    
    @PostMapping
    public Warehouse save(@Valid @RequestBody Warehouse warehouse) {
        return service.save(warehouse);
    }

    @PutMapping("/{id}")
    public Warehouse update(@PathVariable int id, @Valid @RequestBody Warehouse warehouse) {
        return service.update(id, warehouse);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable int id) {
        service.deleteById(id);
    }

}
