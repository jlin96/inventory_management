package com.skillstorm.inventory_management.cucumber;

import static org.junit.jupiter.api.Assertions.assertEquals;

//import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.*;

import com.skillstorm.inventory_management.selenium.Product;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class productSteps {

    private Product product;
    private WebDriver driver;
    private static final String baseUrl = "http://team-6-frontend-jenkins.s3-website-us-east-1.amazonaws.com";
    WebElement e;

    @BeforeMethod
    public void setup(){
        driver = new ChromeDriver();
        driver.get(baseUrl);
    }

    @Test
    public void verifyTitle(){
        String title = driver.getTitle();
        assertEquals("Jesse's Management", title); // Title eventually should reflect Jesse's Management
    }

    @Given("I am on the homepage")
    public void i_am_on_the_homepage(){
        System.out.println("Todo ... Given here...");
    }

    @When("I select the product tab")
    public void i_select_the_product_tab(){
        System.out.println("Todo ... When here");
        product.clickProductTab();
    }

    @Then("I should see the product form")
    public void i_should_see_the_product_form(){
        System.out.println("Todo... Then statement is here...");
    }

    @AfterTest
    public void teardown(){
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        driver.quit();
    }

}
