package com.skillstorm.inventory_management.mappers;

import org.springframework.stereotype.Component;

import com.skillstorm.inventory_management.dtos.ProductDto;
import com.skillstorm.inventory_management.models.Product;

@Component 
public class ProductMapper {
    
    public ProductDto toDto(Product product) {
        return new ProductDto(product.getId(), product.getName(), product.getDescription(), product.getStockAmount(), product.getWarehouse().getId());
    }
}
