package com.skillstorm.inventory_management.cucumber;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import com.skillstorm.inventory_management.selenium.Warehouse;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class WarehouseCRUD {
    
    private WebDriver driver;
    private Warehouse warehouse;
    private static final String url = "http://team-6-frontend-jenkins.s3-website-us-east-1.amazonaws.com";

    @Before("@warehouse")
    public void before() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless=new");
        this.driver = new ChromeDriver(options);
        //this.driver = new ChromeDriver();
        driver.get(url);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        this.warehouse = new Warehouse(driver);
    }



    /*-----------------------------------------------------------------------------*/
    /*        SCENARIO: Successfully navigate to the warehouse page | START        */
    /*-----------------------------------------------------------------------------*/
    @Given("I am on the home page")
    public void i_am_on_the_home_page(){
        assertEquals("HOME", warehouse.getHomeTitle());
    }
    @When("I click the warehouse tab")
    public void i_click_the_warehouse_tab(){
        warehouse.get();
    }
    @Then("I should see the warehouse table")
    public void i_should_see_the_product_table(){
        assertEquals("Warehouses", warehouse.getTableTitle());
    }
    /*-----------------------------------------------------------------------------*/
    /*        SCENARIO: Successfully navigate to the warehouse page | END          */
    /*-----------------------------------------------------------------------------*/




    /*-----------------------------------------------------------------------------*/
    /*        SCENARIO: Clicking add warehouse button               | START        */
    /*-----------------------------------------------------------------------------*/

    @Given("I am on the warehouse tab")
    public void iAmOnWarehouseTab() {
        warehouse.get();
    }

    @When("I click the add warehouse button")
    public void iClickTheAddWarehouseButton() {
        warehouse.addWarehouse();
    }

    @Then("I should see the add warehouse modal")
    public void iShouldSeeTheAddWarehouseModal() {
        assertTrue(warehouse.onForm());
    }
    /*-----------------------------------------------------------------------------*/
    /*        SCENARIO: Clicking add warehouse button               | END          */
    /*-----------------------------------------------------------------------------*/




    /*-----------------------------------------------------------------------------*/
    /*        SCENARIO: Adding warehouse to table                   | START        */
    /*-----------------------------------------------------------------------------*/
    @Given("I am on the add warehouse modal")
    public void iAmOnTheAddWarehouseModal() {
        warehouse.get();
        warehouse.addWarehouse();
        warehouse.addInputs();
    }

    @When("I submit the form")
    public void iSubmitTheForm() {
        warehouse.submitForm();
    }

    @Then("I should see the warehouse show up on the table")
    public void iShouldSeeTheWarehouseShowUpOnTheTable() {
        assertTrue(warehouse.hasWarehouse());
    }
    /*-----------------------------------------------------------------------------*/
    /*        SCENARIO: Adding warehouse to table                   | END          */
    /*-----------------------------------------------------------------------------*/




    /*-----------------------------------------------------------------------------*/
    /*        SCENARIO: Clicking edit icon on a row               | START        */
    /*-----------------------------------------------------------------------------*/
    @When("I click the edit icon on a warehouse row")
    public void iClickTheEditIconOnAWarehouseRow(){
        warehouse.clickEdit();
    }

    @Then("I should see the edit warehouse modal")
    public void iShouldSeeTheEditWarehouseModal() {
        assertTrue(warehouse.onForm());
    }
    /*-----------------------------------------------------------------------------*/
    /*        SCENARIO: Clicking edit icon on a row                 | END          */
    /*-----------------------------------------------------------------------------*/
    


    
    @After("@warehouse")
    public void after() {
        if (this.driver != null) {
            this.driver.quit();
        }
    }
    
    
    @Given("I am on the edit warehouse modal")
    public void iAmOnTheEditWarehouseModal() {
        warehouse.get();
        warehouse.clickEdit();
        warehouse.editInputs();

    }
    
    @Then("I should see the edited warehouse show up on the table")
    public void iShouldSeeTheEditedWarehouseShowUpOnTheTable() {
        assertTrue(warehouse.hasEditedWarehouse());
    }
    
    // @When("I click the delete icon on a warehouse row")
    // public void iClickTheDeleteIconOnAWarehouseRow() {
    //     warehouse.clickDelete();
    // }

    // @Then("I should not see the warehouse show up on the table")
    // public void iShouldNotSeeTheWarehouseShowUpOnTheTable() {
    //     assertFalse(warehouse.hasEditedWarehouse());
    // }

}
