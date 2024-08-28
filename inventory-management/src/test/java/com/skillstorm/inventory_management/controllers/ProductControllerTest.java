package com.skillstorm.inventory_management.controllers;

import com.skillstorm.inventory_management.services.ProductService;
import org.testng.annotations.Test;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import com.skillstorm.inventory_management.dtos.ProductDto;
import com.skillstorm.inventory_management.models.Product;
import com.skillstorm.inventory_management.models.Warehouse;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ProductControllerTest {

    @Mock
    private ProductService service; // Mock

    @InjectMocks
    private ProductController controller; // System under test
    private AutoCloseable closeable;

    @BeforeTest
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        // this.service = mock(ProductService.class);
        // this.controller = new ProductController(service);
    }

    @AfterTest
    public void teardown() throws Exception {
        closeable.close();
    }

    @Test
    void testDeleteById() {

    }

    @Test
    void testFindAll() {

        List<ProductDto> list = new ArrayList<>();
        list.add(new ProductDto(10, "XBOX Series X", "Gaming Console", 50, 1));
        list.add(new ProductDto(11, "Keyboard", "Black Widow LED", 40, 1));
        // Arrange
        when(service.findAll()).thenReturn(list);
        // Act
        List<ProductDto> response = controller.findAll();
        // Assert
        assertEquals(list, response);
    }

    @Test
    void testFindById() {

        Product arbitraProduct = new Product(99, "Truck", "New 2024 Ford", 20, new Warehouse());
        Optional<Product> optionalProduct = Optional.of(arbitraProduct);
        // Arrange
        when(service.findById(anyInt())).thenReturn(optionalProduct);
        // Act
        ResponseEntity<Product> httpStatusResponse = controller.findById(anyInt());
        // Assert
        assertEquals(ResponseEntity.ok(optionalProduct.get()), httpStatusResponse);
    }

    @Test
    void testSave() {

    }

    @Test
    void testUpdate() {

    }

}
