package com.skillstorm.inventory_management.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.skillstorm.inventory_management.models.Product;
import com.skillstorm.inventory_management.dtos.ProductDto;
import com.skillstorm.inventory_management.mappers.ProductMapper;
import com.skillstorm.inventory_management.models.Warehouse;
import com.skillstorm.inventory_management.repositories.ProductRepository;
import com.skillstorm.inventory_management.repositories.WarehouseRepository;

@Service
public class ProductService {

    private ProductRepository repo;
    private WarehouseRepository warehouseRepo;
    private ProductMapper mapper;

    public ProductService(ProductRepository repo, WarehouseRepository warehouseRepo, ProductMapper mapper) {
        this.repo = repo;
        this.warehouseRepo = warehouseRepo;
        this.mapper = mapper;
    }

    public List<ProductDto> findAll() {
        //returns iterable or list
        List<Product> dbProducts = repo.findAll();
        
        List<ProductDto> productDto = dbProducts.stream().map(mapper::toDto).collect(Collectors.toList());
        
        return productDto;
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
        System.out.println(product);
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
