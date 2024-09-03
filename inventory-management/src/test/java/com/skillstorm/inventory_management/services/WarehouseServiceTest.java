package com.skillstorm.inventory_management.services;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;

import java.util.Optional;
import java.util.List;
import java.util.ArrayList;

import com.skillstorm.inventory_management.models.Product;
import com.skillstorm.inventory_management.models.Warehouse;
import com.skillstorm.inventory_management.repositories.WarehouseRepository;

public class WarehouseServiceTest {
    
    @Mock
    private WarehouseRepository repo; // Mock
    @Mock
    private Optional<Warehouse> mockWarehouseOption; // Mock
    @Mock
    private Warehouse mockWarehouse; // Mock
    @Mock
    private Product mockProduct; // Mock
    @InjectMocks
    private WarehouseService service; // System under test
    
    private Warehouse arbitraryWarehouse; // Used whenever we need an arbitrary warehouse in test cases
    private AutoCloseable closeable;
    
    @BeforeTest
    public void setUp() {
        closeable = MockitoAnnotations.openMocks(this);
        arbitraryWarehouse = new Warehouse();
        arbitraryWarehouse.setId(99);
        arbitraryWarehouse.setName("Main Warehouse");
        arbitraryWarehouse.setAddress("5125 NW 36st");
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
        // Assert
        verify(repo).deleteById(anyInt());
    }

    @Test
    void testFindAll() {
        // Arrange
        List<Warehouse> expected = new ArrayList<>();
        expected.add(arbitraryWarehouse);
        when(repo.findAll()).thenReturn(expected);
        // Act
        Iterable<Warehouse> actual = service.findAll();
        // Assert
        Assert.assertEquals(actual, expected);
    }

    @Test
    void testFindAllProduct() {
        // Arrange
        List<Product> list = new ArrayList<>();
        list.add(new Product());
        list.add(new Product());
        arbitraryWarehouse.setProduct(list);
        Optional<Warehouse> optionalWarehouse = Optional.of(arbitraryWarehouse);
        Iterable<Product> expected = list;
        when(repo.findById(anyInt())).thenReturn(optionalWarehouse);
        when(mockWarehouse.getProduct()).thenReturn(list);
        // Act
        Iterable<Product> actual = service.findAllProduct(1);
        // Assert
        Assert.assertEquals(actual,expected);
    }

    @Test
    void testFindById() {
        // Arrange 
        Optional<Warehouse> expected = Optional.of(arbitraryWarehouse);
        when(repo.findById(anyInt())).thenReturn(expected);
        // Act
        Optional<Warehouse> actual = service.findById(1);
        // Assert
        Assert.assertEquals(actual, expected);
    }

    @Test
    void testSave() {
        // Arrange 
        when(repo.save(any(Warehouse.class))).thenReturn(arbitraryWarehouse);
        // Act
        Warehouse actual = service.save(arbitraryWarehouse);
        // Assert
        Assert.assertEquals(actual, arbitraryWarehouse);

    }

    @Ignore
    @Test
    void testUpdate() {
        // Arrange
        Optional<Warehouse> optionalWarehouse = Optional.of(arbitraryWarehouse);
        when(repo.findById(anyInt())).thenReturn(optionalWarehouse);
        
        // Act 
        Warehouse actual = service.update(1, arbitraryWarehouse);
        // Assert
        Assert.assertEquals(actual, arbitraryWarehouse);
    }
}
