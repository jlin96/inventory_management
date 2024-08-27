package com.skillstorm.inventory_management.cucumber;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.autoconfigure.metrics.MetricsProperties.System;
import org.testng.annotations.*;

import com.skillstorm.inventory_management.selenium.Product;
import java.time.Duration;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class productSteps {

    private Product product;
    private WebDriver driver;
    private static final String url = "http://team-6-frontend-jenkins.s3-website-us-east-1.amazonaws.com";
    private int rowsBeforeChanges;

    
    @Before
    public void setup(){
        //System.setProperty("webdriver.chrome.driver", "linuxchromedriver");
        this.driver = new ChromeDriver();
        //this.driver = new FirefoxDriver();
        driver.get(url);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        this.product = new Product(driver);
    }

    @Test
    public void simpleTest(){
        //assertEquals("Jesse's Mangement", product.getRowText());


    }

    /*-----------------------------------------------------------*/
    /* Successfully navigate to the Product Table Scenario Start*/
    /*---------------------------------------------------------- */
    @Given("I am on the homepage")
    public void i_am_on_the_homepage(){
        //assertEquals("Jesse's Mangement", product.verifyTitle());
        assertEquals("HOME", product.getHomeTitle());
    }

    @When("I select the product tab")
    public void i_select_the_product_tab(){
        //System.out.println("Selecting product tab from side menu...");
        product.clickProductTab();
    }

    @Then("I should see the product table")
    public void i_should_see_the_product_table(){
        //System.out.println("Comparing table title to test if it matches Products...");
        //assertEquals("Jesse's Mangement", product.verifyTitle());
        assertEquals("Products", product.getTableTitle());
    }
    /*--------------------------------------------------------*/
    /* Successfully navigate to the Product Table Scenario End*/
    /*--------------------------------------------------------*/


    /* ----------------------------------------------------------
     * Successfully navigate to the product form Scenario Start--
     *----------------------------------------------------------*/

    @Given("I am on the product page")
    public void i_am_on_the_product_page(){
        //System.out.println("Given i am on the product page i should see the product table")
        assertEquals("HOME", product.getHomeTitle());
        product.clickProductTab();
        assertEquals("Products", product.getTableTitle());
    }

    @When("I click the Add Product button")
    public void i_click_the_Add_Product_button(){
        product.clickAddProductButton();
    }

    @Then("I should see the product form")
    public void i_should_see_the_product_form(){
        assertEquals(true, product.isProductForm());
       
    }

    /* ----------------------------------------------------------
     * Successfully navigate to the product form Scenario Start--
     *----------------------------------------------------------*/


     /* ----------------------------------------------------------
     * Successfully create a product Scenario Start--
     *----------------------------------------------------------*/
    @Given("I am on the product form")
    public void i_am_on_the_product_form(){
        assertEquals("HOME", product.getHomeTitle());
        product.clickProductTab();
        rowsBeforeChanges = product.getRows();
        assertEquals("Products", product.getTableTitle());
        product.clickAddProductButton();
        assertEquals(true, product.isProductForm());
    }

    @When("I fill in the name")
    public void i_fill_in_the_name(){
        product.fillName();
    }

    @And("I fill in the description")
    public void i_fill_in_the_description(){
        product.fillDescription();
    }

    @And("I fill in the stock")
    public void i_fill_in_the_stock(){
        product.fillStock();
    }

    @And("I fill in the warehouse with an existing warehouse id")
    public void i_fill_in_the_warehouse_with_an_existing_warehouse_id(){
        product.fillWarehouse();
    }

    @And("i click the submit button")
    public void i_click_the_submit_button(){
        //rowsBeforeChanges = product.getRows();
        product.submitForm();
    }

    @Then("my new product should be created")
    public void my_new_product_should_be_created(){
        
        //System.out.println("Rows before changes : " + rowsBeforeChanges);
        assertEquals((rowsBeforeChanges + 1), product.getRows());
    }

    /* ----------------------------------------------------------
     * Successfully create a product Scenario End
     *----------------------------------------------------------*/

    /*-----------------------------------------------------------
     * Successfully delete a product START
     *----------------------------------------------------------*/

    @When("I double click the delete button")
    public void i_double_click_the_delete_button(){
        rowsBeforeChanges = product.getRows();
        product.clickDeleteButton();
    }

    @Then("the product should be deleted")
    public void the_product_should_be_deleted(){
        assertEquals(rowsBeforeChanges - 1, product.getRows());
    }

    /*-----------------------------------------------------------
     * Successfully delete a product END
     *----------------------------------------------------------*/

    @After
    public void teardown(){
       product.quitDriver();
    }
}