package com.skillstorm.inventory_management.controllers;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.actuate.autoconfigure.metrics.MetricsProperties.System;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.List;
import java.util.ArrayList;
import java.util.Optional;

import com.skillstorm.inventory_management.models.Product;
import com.skillstorm.inventory_management.models.Warehouse;
import com.skillstorm.inventory_management.services.WarehouseService;

public class WarehouseControllerTest {

    @Mock
    private WarehouseService service; // Mock

    @InjectMocks
    private WarehouseController controller; // System under test

    private AutoCloseable closeable;
    private Warehouse arbitrarWarehouse;

    @BeforeTest
    public void setUp() {
        closeable = MockitoAnnotations.openMocks(this);
        arbitrarWarehouse = new Warehouse();
        arbitrarWarehouse.setId(99);
        arbitrarWarehouse.setName("Arbitrary Name");
        arbitrarWarehouse.setAddress("5136 Main St");
    }

    @AfterTest
    public void teardown() throws Exception {
        closeable.close();
    }

    @Test
    void testDeleteById() {
        // Arrange
        doNothing().when(service).deleteById(anyInt());
        // Act
        controller.deleteById(anyInt());
        // Assert
        verify(service).deleteById(anyInt());
    }

    @Test
    void testFindAll() {
        // Arrange
        List<Warehouse> list = new ArrayList<>();
        list.add(arbitrarWarehouse);
        Iterable<Warehouse> expected = list; 
        when(service.findAll()).thenReturn(expected);
        // Act
        Iterable<Warehouse> actual = controller.findAll();
        // Assert
        Assert.assertEquals(actual, expected);
        verify(service).findAll();
    }

    @Test
    void testFindAllProduct() {
        // Arrange
        List<Product> list = new ArrayList<>();
        list.add(new Product());
        Iterable<Product> expected = list;
        when(service.findAllProduct(anyInt())).thenReturn(expected);
        // Act
        Iterable<Product> actual = controller.findAllProduct(1);
        // Assert
        Assert.assertEquals(actual, expected);
        verify(service).findAllProduct(anyInt());
    }

    @Test(priority = 1)
    void testFindByIdSunnyDay() {
        // Arrange
        Optional<Warehouse> optionalWarehouse = Optional.of(arbitrarWarehouse); 
        when(service.findById(anyInt())).thenReturn(optionalWarehouse);
        // Act 
        ResponseEntity<Warehouse> actual = controller.findById(1);
        // Assert
        Assert.assertEquals(actual.getStatusCode(), HttpStatus.OK);
        verify(service).findById(anyInt());
    }

    @Test(priority = 2)
    void testFindByIdRainyDay() {
        // Arrange
        Optional<Warehouse> optionalNullWarehouse = Optional.ofNullable(null); 
        when(service.findById(anyInt())).thenReturn(optionalNullWarehouse);
        // Act 
        ResponseEntity<Warehouse> actual = controller.findById(1);
        // Assert
        Assert.assertEquals(actual.getStatusCode(), HttpStatus.NOT_FOUND);
        verify(service, times(2)).findById(anyInt());
    }

    @Test
    void testSave() {
        // Arrange
        when(service.save(any(Warehouse.class))).thenReturn(arbitrarWarehouse);
        // Act
        Warehouse actual = controller.save(arbitrarWarehouse);
        // Assert
        Assert.assertEquals(actual, arbitrarWarehouse);
    }

    @Test
    void testUpdate() {
        // Arrange
        when(service.update(anyInt(), any(Warehouse.class))).thenReturn(arbitrarWarehouse);
        // Act
        Warehouse actual = controller.update(0, arbitrarWarehouse);
        // Assert
        Assert.assertEquals(actual, arbitrarWarehouse);
    }
}
