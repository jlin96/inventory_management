package com.skillstorm.inventory_management.cucumber;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.concurrent.TimeUnit;

//import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.*;

import com.skillstorm.inventory_management.selenium.Product;
import java.time.Duration;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class productSteps {

    private Product product;
    private WebDriver driver;
    private static final String url = "http://team-6-frontend-jenkins.s3-website-us-east-1.amazonaws.com";

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
        //assertEquals("Jesse's Mangement", product.verifyTitle());
    }
    /* Successfully navigate to the Product Table Scenario Start*/
    @Given("I am on the homepage")
    public void i_am_on_the_homepage(){
        //assertEquals("Jesse's Mangement", product.verifyTitle());
        assertEquals("HOME", product.getHomeTitle());
    }

    @When("I select the product tab")
    public void i_select_the_product_tab(){
        System.out.println("Selecting product tab from side menu...");
        product.clickProductTab();
    }

    @Then("I should see the product table")
    public void i_should_see_the_product_table(){
        System.out.println("Comparing table title to test if it matches Products...");
        //assertEquals("Jesse's Mangement", product.verifyTitle());
        assertEquals("Products", product.getTableTitle());
    }
    /* Successfully navigate to the Product Table Scenario End*/

    @After
    public void teardown(){
       product.quitDriver();
    }
}
