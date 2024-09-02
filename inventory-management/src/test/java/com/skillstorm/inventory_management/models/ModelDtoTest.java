package com.skillstorm.inventory_management.models;

import org.junit.jupiter.api.DisplayName;
import org.meanbean.test.BeanTester;
import org.testng.annotations.Test;

import com.skillstorm.inventory_management.dtos.ProductDto;
import com.skillstorm.inventory_management.mappers.ProductMapper;

/*
 * This class uses the MeanBean library that simplifies testing POJO's
 *  getters, setters, constructors, equal, and hashcode method.
 */
public class ModelDtoTest {

    @Test
    @DisplayName("Test all model and DTO")
    public void modelDtoAllTest(){
        
        final Class<?>[] domainClasses = {Product.class, Warehouse.class, ProductDto.class};
        BeanTester tester = new BeanTester();
        for ( Class <?> domainClass : domainClasses){
            // This method will test setter, getters, and constructor for listed classes
            tester.testBean(domainClass);
        }
    }
}
