package com.skillstorm.inventory_management.services;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.skillstorm.inventory_management.models.Warehouse;
import com.skillstorm.inventory_management.repositories.WarehouseRepository;

@Service
public class WarehouseService {

    private WarehouseRepository repo;

    public WarehouseService(WarehouseRepository repo) {
        this.repo = repo;
    }

    public Iterable<Warehouse> findAll() {
        return repo.findAll();
    }

    public Optional<Warehouse> findById(int id) {
        return repo.findById(id);
    }

    public Warehouse save(Warehouse warehouse) {
        return repo.save(warehouse);
    }

    public Warehouse update(int id, Warehouse warehouse) {
        Optional<Warehouse> dbWarehouse = repo.findById(id);
        
        //Error handling incase it does not exist in db
        Warehouse foundWarehouse = dbWarehouse.get();

        foundWarehouse.setAddress(warehouse.getAddress());
        foundWarehouse.setName(warehouse.getName());

        return repo.save(foundWarehouse);
    }

    public void deleteById(int id) {
        repo.deleteById(id);
    }

    //Get List of products according to warehouse
}
