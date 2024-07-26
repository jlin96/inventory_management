package com.skillstorm.inventory_management.services;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.skillstorm.inventory_management.models.Product;
import com.skillstorm.inventory_management.repositories.ProductRepository;

@Service
public class ProductService {

    private ProductRepository repo;

    public ProductService(ProductRepository repo) {
        this.repo = repo;
    }

    public Iterable<Product> findAll() {
        return repo.findAll();
    }

    public Optional<Product> findById(int id) {
        return repo.findById(id);
    }

    public Product save(Product product) {
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
