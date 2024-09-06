package com.skillstorm.inventory_management.cucumber;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;

import com.skillstorm.inventory_management.selenium.Warehouse;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class WarehouseSteps {
    
    private WebDriver driver;
    private Warehouse warehouse;
    private int rowsBeforeChanges;
    private static final String url = "http://team-6-frontend-jenkins.s3-website-us-east-1.amazonaws.com";

    @Before("@warehouse")
    public void setup() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless=new");
        this.driver = new ChromeDriver(options);
        //this.driver = new ChromeDriver();
        driver.get(url);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        this.warehouse = new Warehouse(driver);
    }



    /*-----------------------------------------------------------------------------*/
    /*      BACKGROUND: Successfully navigate to the warehouse page | START        */
    /*-----------------------------------------------------------------------------*/
    @Given("I am on the home page")
    public void i_am_on_the_home_page(){
        assertEquals("HOME", warehouse.getHomeTitle());
    }
    @When("I click the warehouse tab")
    public void i_click_the_warehouse_tab(){
        warehouse.clickWarehouseTab();
    }
    @Then("I should see the warehouse table")
    public void i_should_see_the_product_table(){
        assertEquals("Warehouses", warehouse.getTableTitle());
    }
    /*-----------------------------------------------------------------------------*/
    /*      BACKGROUND: Successfully navigate to the warehouse page | END          */
    /*-----------------------------------------------------------------------------*/




    /*-----------------------------------------------------------------------------*/
    /*      SCENARIO: Successfully navigate to the warehouse form   | START        */
    /*-----------------------------------------------------------------------------*/
    @When("I click the add warehouse button")
    public void i_click_the_add_warehouse_button(){
        rowsBeforeChanges = warehouse.getRows();
        warehouse.addWarehouseButtonClick();
    }
    @Then("I should see the warehouse form")
    public void i_should_see_the_warehouse_form(){
        Assert.assertTrue(warehouse.onForm());
    }
    /*-----------------------------------------------------------------------------*/
    /*      SCENARIO: Successfully navigate to the warehouse form   | END          */
    /*-----------------------------------------------------------------------------*/




    /*-----------------------------------------------------------------------------*/
    /*SCENARIO OUTLINE: Successfully add a warehouse to the table   | START        */
    /*-----------------------------------------------------------------------------*/
    @When("I fill in the name input with {string}")
    public void i_fill_in_the_name_input_with(String name) {
    warehouse.addNameInput(name);
    }
    @When("I fill in the address input with {string}")
    public void i_fill_in_the_address_input_with(String address) {
    warehouse.addAddressInput(address);
    }
    @When("I click submit the form button")
    public void i_click_submit_the_form_button() {
    warehouse.clickSubmitWarehouseButton();
    }
    @Then("I should see the warehouse {string} and {string} show up on the table")
    public void iShouldSeeTheWarehouseShowUpOnTheTable(String name, String address) {
        Assert.assertEquals(rowsBeforeChanges + 1, warehouse.getRows());
    }
    /*-----------------------------------------------------------------------------*/
    /*SCENARIO OUTLINE: Successfully add a warehouse to the table   | END          */
    /*-----------------------------------------------------------------------------*/




    /*-----------------------------------------------------------------------------*/
    /*        SCENARIO: Successfully delete a warehouse             | START        */
    /*-----------------------------------------------------------------------------*/
    @Given("there exist at least one warehouse in the table")
    public void there_exist_at_least_one_warehouse_in_the_table() {
        
        if (rowsBeforeChanges > 0)
            System.out.println("There does exist at least 1 product.\n");
        else
            System.out.print("Nothing to delete.\n");
    }
    @When("I double click the delete button in the warehouse table")
    public void i_double_click_the_delete_button_in_the_warehouse_table() {
        rowsBeforeChanges = warehouse.getRows();
        if (rowsBeforeChanges > 0)
            warehouse.clickDeleteButton();
        else
            System.out.print("Nothing to delete.\n");
    }
    @Then("the warehouse should be deleted")
    public void the_warehouse_should_be_deleted() {
        //System.out.println("Rows before : " + rowsBeforeChanges);
        //System.out.println("Rows after : " + warehouse.getRows());

        //if (rowsBeforeChanges > 0)
        //    Assert.assertEquals(warehouse.getRows(), warehouse.getRows());
        //else
        //    System.out.print("Nothing to delete.\n");
    }
    /*-----------------------------------------------------------------------------*/
    /*        SCENARIO: Successfully delete a warehouse             | END          */
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
        Assert.assertTrue(warehouse.hasEditedWarehouse());
    }
    /*-----------------------------------------------------------------------------*/
    /*        SCENARIO: Clicking edit icon on a row                 | END          */
    /*-----------------------------------------------------------------------------*/
    

    @Given("I am on the edit warehouse modal")
    public void iAmOnTheEditWarehouseModal() {

        warehouse.clickEdit();
        warehouse.editInputs();
    }
    
    @Then("I should see the edited warehouse show up on the table")
    public void iShouldSeeTheEditedWarehouseShowUpOnTheTable() {
        Assert.assertTrue(warehouse.hasEditedWarehouse());
    }
    
    @After("@warehouse")
    public void after() {
        if (this.driver != null) {
            this.driver.quit();
        }
    }

}
