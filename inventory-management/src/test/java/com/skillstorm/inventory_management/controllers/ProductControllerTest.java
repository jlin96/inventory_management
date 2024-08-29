package com.skillstorm.inventory_management.controllers;

import com.skillstorm.inventory_management.services.ProductService;
import org.testng.annotations.Test;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Ignore;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.internal.stubbing.answers.DoesNothing;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.skillstorm.inventory_management.dtos.ProductDto;
import com.skillstorm.inventory_management.models.Product;
import com.skillstorm.inventory_management.models.Warehouse;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

//controller test
public class ProductControllerTest {

    @Mock
    private ProductService service; // Mock

    @InjectMocks
    private ProductController controller; // System under test
    private AutoCloseable closeable;
    Product arbitryProduct;

    @BeforeTest
    public void setUp() {
        closeable = MockitoAnnotations.openMocks(this);
        arbitryProduct = new Product(99, "Truck", "New 2024 Ford", 20, new Warehouse());
    }

    @AfterTest
    public void teardown() throws Exception {
        closeable.close();
    }

    @Test
    public void testDeleteById() {
        int expected = 1;
        // Arrange
        doNothing().when(service).deleteById(anyInt());
        // Act
        int actual = controller.deleteById(expected);
        // Assert 
        assertEquals(expected, actual);
        verify(service).deleteById(anyInt());
    }

    @Test
    public void testFindAll() {

        List<ProductDto> list = new ArrayList<>();
        list.add(new ProductDto(10, "XBOX Series X", "Gaming Console", 50, 1));
        list.add(new ProductDto(11, "Keyboard", "Black Widow LED", 40, 1));
        // Arrange
        when(service.findAll()).thenReturn(list);
        // Act
        List<ProductDto> response = controller.findAll();
        // Assert
        assertEquals(list, response);
        verify(service).findAll();
    }

    
    @Test
    public void testFindByIdSunnyDay() {
        Optional<Product> optionalProduct = Optional.of(arbitryProduct);
        // Arrange
        when(service.findById(anyInt())).thenReturn(optionalProduct);
        // Act
        ResponseEntity<Product> httpStatusResponse = controller.findById(anyInt());
        // Assert
        assertEquals(ResponseEntity.ok(optionalProduct.get()), httpStatusResponse);
        //verify(service).findById(anyInt());
    }

    @Test
    public void testFindByIdRainyDay(){
        // Arange
        when(service.findById(anyInt())).thenReturn(Optional.ofNullable(null));
        // Act 
        ResponseEntity<Product> httpStatusResponse = controller.findById(anyInt());
        // Assert
        assertEquals(new ResponseEntity<>(HttpStatus.NOT_FOUND), httpStatusResponse);
        verify(service).findById(anyInt());
    }

    
    @Test
    public void testSaveSunnyDay() {
        // Arrange
        when(service.save(any(Product.class))).thenReturn(arbitryProduct);
        // Act 
        Product expected = controller.save(arbitryProduct);
        // Assert
        assertEquals(expected, arbitryProduct);
        //verify(service).save(any(Product.class));
    }

    
    @Test
    public void testSaveRainyDay(){
        // Arrange
        when(service.save(any(Product.class))).thenReturn(null);
        // Act
        Product expected = controller.save(arbitryProduct);
        //Assert
        assertEquals(expected, null);
        verify(service).save(any(Product.class));
    }


    @Test
    public void testUpdate() {
        //Arrange
        when(service.update(anyInt(), any(Product.class))).thenReturn(arbitryProduct);
        // Act
        Product expected = controller.update(1,arbitryProduct);
        //Assert
        assertEquals(expected, arbitryProduct);
        verify(service).update(anyInt(),any(Product.class));
    }

}
