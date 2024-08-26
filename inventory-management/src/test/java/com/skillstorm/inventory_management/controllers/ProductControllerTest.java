package com.skillstorm.inventory_management.controllers;
import com.skillstorm.inventory_management.services.ProductService;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeTest;
import org.mockito.Mockito;
import com.skillstorm.inventory_management.dtos.ProductDto;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

public class ProductControllerTest {

    private ProductController controller; // System under test
    private ProductService service;       // Mock

    @BeforeTest
    public void setUp(){
        this.service = mock(ProductService.class);
        this.controller = new ProductController(service);
    }


    @Test
    void testDeleteById() {

    }

    @Test
    void testFindAll() {

        List<ProductDto> list = new ArrayList<>();
        list.add(new ProductDto(10, "XBOX Series X", "Gaming Console", 50, 1 ));
        list.add(new ProductDto(11, "Keyboard", "Black Widow LED", 40, 1));
        
        // Arrange
        when(service.findAll()).thenReturn(list);

        //Act
        List<ProductDto> response = controller.findAll();

        //Assert
        assertNotNull(response);
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
