package com.skillstorm.inventory_management.cucumber;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

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

    @Before("@warehouse")
    public void before() {
        ChromeOptions options = new ChromeOptions();
        driver = new ChromeDriver(options);
        this.driver = new ChromeDriver();
        this.warehouse = new Warehouse(driver);
    }

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

    @When("I click the edit icon on a warehouse row")
    public void iClickTheEditIconOnAWarehouseRow(){
        warehouse.clickEdit();
    }

    @Then("I should see the edit warehouse modal")
    public void iShouldSeeTheEditWarehouseModal() {
        assertTrue(warehouse.onForm());
    }
    
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
