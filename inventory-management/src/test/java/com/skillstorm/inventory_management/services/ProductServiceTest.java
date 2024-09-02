package com.skillstorm.inventory_management.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.testng.Assert;
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
    @Mock 
    private Warehouse mockWarehouse;
    @Mock
    private Product mProduct;
    @Mock
    private Optional<Warehouse> mOptWarehouse;
    @InjectMocks
    private ProductService service;  // System under test
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
    public void testDeleteById() {
        // Arrange
        doNothing().when(repo).deleteById(anyInt());
        // Act
        service.deleteById(1);
        //Assert
        verify(repo).deleteById(anyInt());
    }

    @Test
    public void testFindAll() {
        // Arrange
        List<Product> list = new ArrayList<>();
        list.add(arbitraryProduct);
        when(repo.findAll()).thenReturn(list);
        // Act
        List<ProductDto> productDto = service.findAll();
        // Assert
        assertNotNull(productDto);
        verify(repo).findAll();
    }

    // Priority 1 set to ensure it runs before Rainy day in respect to verify method
    @Test(priority=1)
    public void testFindByIdSunnyDay() {
        // Arrange
        Optional<Product> optionalProduct = Optional.of(arbitraryProduct); 
        when(repo.findById(anyInt())).thenReturn(optionalProduct);
        // Act
        Optional<Product> response = service.findById(anyInt());
        // Assert
        assertEquals(optionalProduct, response);
        verify(repo).findById(anyInt());
    }

    // Priority 2 set to ensure it runs after Sunny day in respect to verify method
    @Test(priority=2)
    public void testFindByIdRainyDay(){
        // Arrange 
        Optional<Product> optionalProduct = Optional.ofNullable(null);
        when(repo.findById(anyInt())).thenReturn(optionalProduct);
        // Act
        Optional<Product> response = service.findById(anyInt());
        // Assert
        assertEquals(Optional.empty(), response);
        verify(repo, times(2)).findById(anyInt());
    }

    @Test
    public void testSaveSunnyDay() {
        // Arrange 
        Optional<Warehouse> optionalWarehouse = Optional.of(arbitraryWarehouse);
        //when(mProduct.getWarehouse()).thenReturn(arbitraryWarehouse);
        when(warehouseRepo.findById(anyInt())).thenReturn(optionalWarehouse);
        when(repo.save(any(Product.class))).thenReturn(arbitraryProduct);
        // Act 
        Product actual = service.save(arbitraryProduct);
        // Assert
        Assert.assertEquals(actual, arbitraryProduct);
    }

    @Test
    public void testSaveRainyDay() {
        // Arrange 
        Optional<Warehouse> optionalWarehouse = Optional.ofNullable(null);
        //when(mProduct.getWarehouse()).thenReturn(arbitraryWarehouse);
        when(warehouseRepo.findById(anyInt())).thenReturn(optionalWarehouse);
        when(repo.save(any(Product.class))).thenReturn(arbitraryProduct);
        // Act 
        Product actual = service.save(arbitraryProduct);
        // Assert
        Assert.assertEquals(actual, null);
    }

    @Test(priority = 3)
    public void testUpdate() {
        // Arrange
        Optional<Product> optionalProduct = Optional.of(arbitraryProduct);
        when(repo.findById(anyInt())).thenReturn(optionalProduct);
        when(repo.save(arbitraryProduct)).thenReturn(arbitraryProduct);
        // Act
        Product actual = service.update(1, arbitraryProduct);
        // Assert
        Assert.assertEquals(actual, arbitraryProduct);
    }
}
