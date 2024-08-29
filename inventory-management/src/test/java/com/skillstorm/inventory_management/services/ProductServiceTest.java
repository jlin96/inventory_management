package com.skillstorm.inventory_management.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.skillstorm.inventory_management.dtos.ProductDto;
import com.skillstorm.inventory_management.mappers.ProductMapper;
import com.skillstorm.inventory_management.models.Product;
import com.skillstorm.inventory_management.models.Warehouse;
import com.skillstorm.inventory_management.repositories.ProductRepository;
import com.skillstorm.inventory_management.repositories.WarehouseRepository;

public class ProductServiceTest {

    @Mock
    private ProductRepository repo;
    @Mock
    private WarehouseRepository warehouseRepo;
    @Mock
    private ProductMapper mapper;
    @InjectMocks
    private ProductService service;
    private AutoCloseable closeable;
    private Product arbitraryProduct;
    private Warehouse arbitraryWarehouse;

    @BeforeTest
    public void setUp() {
        closeable = MockitoAnnotations.openMocks(this);
        arbitraryWarehouse = new Warehouse();
        arbitraryWarehouse.setId(99);
        arbitraryWarehouse.setName("Main Warehouse");
        arbitraryWarehouse.setAddress("5125 NW 36st");
        arbitraryProduct = new Product();
        arbitraryProduct.setId(99);
        arbitraryProduct.setName("TCL 32'' Television");
        arbitraryProduct.setDescription("Home Entertainment");
        arbitraryProduct.setStockAmount(50);
        arbitraryProduct.setWarehouse(arbitraryWarehouse);
    }

    @AfterTest
    public void teardown() throws Exception {
        closeable.close();
    }

    @Test
    void testDeleteById() {
        // Arrange
        doNothing().when(repo).deleteById(anyInt());
        // Act
        service.deleteById(1);
        //Assert
        verify(repo).deleteById(anyInt());
    }

    @Test
    void testFindAll() {

        List<Product> list = new ArrayList<>();
        Product arbitraryProduct = new Product();
        list.add(arbitraryProduct);
        // Arrange
        when(repo.findAll()).thenReturn(list);
        // Act
        List<ProductDto> productDto = service.findAll();
        // Assert
        assertNotNull(productDto);
        verify(repo).findAll();
    }

    @Test
    void testFindById() {

    }

    @Test
    void testSave() {

    }

    @Test
    void testUpdate() {

    }
}
