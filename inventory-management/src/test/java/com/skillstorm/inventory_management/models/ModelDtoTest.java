package com.skillstorm.inventory_management.models;

import org.junit.jupiter.api.DisplayName;
import org.meanbean.test.BeanTester;
import org.testng.annotations.Test;

import com.skillstorm.inventory_management.dtos.ProductDto;

public class ModelDtoTest {

    @Test
    @DisplayName("Test all model and DTO")
    public void modelDtoAllTest(){

        final Class<?>[] domainClasses = {Product.class, Warehouse.class, ProductDto.class};
        BeanTester tester = new BeanTester();
        for ( Class <?> domainClass : domainClasses){
            tester.testBean(domainClass);
        }
       
        //new BeanTester().testBean(Product.class);
        //new BeanTester().testBean(Warehouse.class);
        //new BeanTester().testBean(ProductDto.class);
        //new EqualsMethodTester().testEqualsMethod(Product.class);
        //new EqualsMethodTester().testEqualsMethod(Warehouse.class);
        //new HashCodeMethodTester().testHashCodeMethod(Product.class);
        //new HashCodeMethodTester().testHashCodeMethod(Warehouse.class);
        //BeanVerifier.verifyBeans(Product.class, Warehouse.class);
    }
}
