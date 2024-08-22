package com.skillstorm.inventory_management.cucumber;

import static org.junit.jupiter.api.Assertions.assertEquals;

//import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.*;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class productSteps {

    private WebDriver driver;
    private static final String baseUrl = "http://team-6-frontend-1.s3-website-us-east-1.amazonaws.com/";
    WebElement e;

    @BeforeMethod
    public void setup(){
        driver = new ChromeDriver();
        driver.get(baseUrl);
    }

    @Test
    public void verifyTitle(){
        String title = driver.getTitle();
        assertEquals("Vite + React", title); // Title eventuallu should reflect Jesse's Management
    }

    @Given("I am on the homepage")
    public void i_am_on_the_homepage(){
        System.out.println("Given test here...");
    }

    @When("I select the product tab")
    public void i_select_the_product_tab(){
        System.out.println("I am in the When statement...");
    }

    @Then("I should see the product form")
    public void i_should_see_the_product_form(){
        System.out.println("The Then statement is here...");
    }

    @AfterTest
    public void teardown(){
        driver.quit();
    }

}
