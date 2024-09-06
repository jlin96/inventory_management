package com.skillstorm.inventory_management.controllers;

import com.skillstorm.inventory_management.services.ProductService;
import org.testng.annotations.Test;
import org.testng.Assert;                                    
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.skillstorm.inventory_management.dtos.ProductDto;
import com.skillstorm.inventory_management.models.Product;
import com.skillstorm.inventory_management.models.Warehouse;


import static org.mockito.ArgumentMatchers.any;
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
    private Product arbitraryProduct;

    @BeforeTest
    public void setUp() {
        closeable = MockitoAnnotations.openMocks(this);
        arbitraryProduct = new Product(99, "Truck", "New 2024 Ford", 20, new Warehouse());
    }

    @AfterTest
    public void teardown() throws Exception {
        closeable.close();
    }

    @Test
    public void testDeleteById() {
        // Arrange
        int expected = 1;
        doNothing().when(service).deleteById(anyInt());
        // Act
        int actual = controller.deleteById(expected);
        // Assert 
        Assert.assertEquals(actual, expected); 
        verify(service).deleteById(anyInt());
    }

    @Test
    public void testFindAll() {
        // Arrange
        List<ProductDto> expected = new ArrayList<>();
        expected.add(new ProductDto(10, "XBOX Series X", "Gaming Console", 50, 1));
        expected.add(new ProductDto(11, "Keyboard", "Black Widow LED", 40, 1));
        when(service.findAll()).thenReturn(expected);
        // Act
        List<ProductDto> actual = controller.findAll();
        // Assert
        Assert.assertEquals(actual, expected);
        verify(service).findAll();
    }
    
    @Test(priority = 1)
    public void testFindByIdSunnyDay() {
        // Arrange
        Optional<Product> optionalProduct = Optional.of(arbitraryProduct);
        when(service.findById(anyInt())).thenReturn(optionalProduct);
        // Act
        ResponseEntity<Product> actual = controller.findById(anyInt());
        // Assert
        Assert.assertEquals(actual, ResponseEntity.ok(optionalProduct.get()));
        verify(service).findById(anyInt());
    }

    @Test(priority = 2)
    public void testFindByIdRainyDay(){
        // Arange
        when(service.findById(anyInt())).thenReturn(Optional.ofNullable(null));
        // Act 
        ResponseEntity<Product> httpStatusResponse = controller.findById(anyInt());
        // Assert
        Assert.assertEquals(new ResponseEntity<>(HttpStatus.NOT_FOUND), httpStatusResponse);
        verify(service, times(2)).findById(anyInt());
    }
    
    @Test(priority = 1)
    public void testSaveSunnyDay() {
        // Arrange
        when(service.save(any(Product.class))).thenReturn(arbitraryProduct); 
        // Act 
        Product actual = controller.save(arbitraryProduct);
        // Assert
        Assert.assertEquals(actual, arbitraryProduct);
        verify(service).save(any(Product.class));
    }
    
    @Test(priority = 2)
    public void testSaveRainyDay(){
        // Arrange
        when(service.save(any(Product.class))).thenReturn(null);
        // Act
        Product actual = controller.save(arbitraryProduct);
        //Assert
        Assert.assertEquals(actual, null);
        verify(service,times(2)).save(any(Product.class));
    }

    @Test
    public void testUpdate() {
        //Arrange
        when(service.update(anyInt(), any(Product.class))).thenReturn(arbitraryProduct);
        // Act
        Product actual = controller.update(1,arbitraryProduct);
        //Assert
        Assert.assertEquals(actual, arbitraryProduct);
        verify(service).update(anyInt(),any(Product.class));
    }

}
