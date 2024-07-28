package com.skillstorm.inventory_management.services;

import java.util.Optional;

import javax.swing.text.html.Option;

import org.springframework.stereotype.Service;

import com.skillstorm.inventory_management.models.Product;
import com.skillstorm.inventory_management.models.Warehouse;
import com.skillstorm.inventory_management.repositories.ProductRepository;
import com.skillstorm.inventory_management.repositories.WarehouseRepository;

@Service
public class ProductService {

    private ProductRepository repo;
    private WarehouseRepository warehouseRepo;

    public ProductService(ProductRepository repo, WarehouseRepository warehouseRepo) {
        this.repo = repo;
        this.warehouseRepo = warehouseRepo;
    }

    public Iterable<Product> findAll() {
        return repo.findAll();
    }

    public Optional<Product> findById(int id) {
        return repo.findById(id);
    }

    public Product save(Product product) {
        Warehouse savedWarehouse = product.getWarehouse();
        Optional<Warehouse> dbWarehouse = warehouseRepo.findById(savedWarehouse.getId());

        if(dbWarehouse.isPresent()) {
            product.setWarehouse(dbWarehouse.get());
        } else {
            return null;
        }

        return repo.save(product);
    }

    public Product update(int id, Product product) {
        Optional<Product> dbProduct = repo.findById(id);

        //Error handling for if it doesn't exist
        Product foundProduct = dbProduct.get();

        foundProduct.setDescription(product.getDescription());
        foundProduct.setName(product.getName());

        //Might be better to have this as a seperate service?
        //No need to set all properties again if just updating stock?
        foundProduct.setStockAmount(product.getStockAmount());

        return repo.save(foundProduct);
    }

    public void deleteById(int id) {
        repo.deleteById(id);
    }
}
