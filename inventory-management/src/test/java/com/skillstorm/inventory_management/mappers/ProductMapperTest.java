package com.skillstorm.inventory_management.mappers;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.skillstorm.inventory_management.controllers.ProductController;
import com.skillstorm.inventory_management.dtos.ProductDto;
import com.skillstorm.inventory_management.models.Product;
import com.skillstorm.inventory_management.models.Warehouse;
import com.skillstorm.inventory_management.services.ProductService;

public class ProductMapperTest {

    Product arbitraryProduct;
    ProductMapper productMapper;
    ProductDto productDto;

    @BeforeTest
    public void setUp() {
        productMapper = new ProductMapper();
        arbitraryProduct = new Product(99, "Truck", "New 2024 Ford", 20, new Warehouse());
    }

    @Test
    void testToDto() {    
        productDto = productMapper.toDto(arbitraryProduct);
        Assert.assertEquals(productDto.getId(), arbitraryProduct.getId());
        Assert.assertEquals(productDto.getName(), arbitraryProduct.getName());
        Assert.assertEquals(productDto.getDescription(), arbitraryProduct.getDescription());
        Assert.assertEquals(productDto.getStockAmount(), arbitraryProduct.getStockAmount());
        Assert.assertEquals(productDto.getWarehouseId(), arbitraryProduct.getWarehouse().getId());
    }
}
