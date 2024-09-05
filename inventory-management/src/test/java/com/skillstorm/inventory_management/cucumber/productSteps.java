package com.skillstorm.inventory_management.cucumber;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;

import com.skillstorm.inventory_management.selenium.Product;
import java.time.Duration;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.java.lu.a.as;


public class productSteps {

    private Product product;
    private WebDriver driver;
    private static final String url = "http://team-6-frontend-jenkins.s3-website-us-east-1.amazonaws.com";
    private int rowsBeforeChangesProduct;

    @Before("@product")
    public void setup(){
        ChromeOptions option = new ChromeOptions();
        option.addArguments("--headless=new");
        //this.driver = new ChromeDriver(option);
        this.driver = new ChromeDriver();
        driver.get(url);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        this.product = new Product(driver);
        System.out.println("--------- SCENARIO START ----------");
    }

    /*-----------------------------------------------------------------------------*/
    /*        BACKGROUND: Successfully navigate to the product page | START        */
    /*-----------------------------------------------------------------------------*/
    @Given("I am on the homepage")
    public void i_am_on_the_homepage(){
        assertEquals("HOME", product.getHomeTitle());
    }
    @When("I select the product tab")
    public void i_select_the_product_tab(){
        product.clickProductTab();
    }
    @Then("I should see the product table")
    public void i_should_see_the_product_table(){
        assertEquals("Products", product.getTableTitle());
    }
    /*-----------------------------------------------------------------------------*/
    /*        BACKGROUND: Successfully navigate to the product page | END          */
    /*-----------------------------------------------------------------------------*/



    /*-----------------------------------------------------------------------------*/
    /*        SCENARIO: Successfully navigate to the product form   | START        */
    /*-----------------------------------------------------------------------------*/
    @When("I click the add product button")
    public void i_click_the_add_product_button(){
        // rowsBeforeChanges is later referenced to verify delete and create changes
        this.rowsBeforeChangesProduct = product.getRows();
        product.clickAddProductButton();
    }
    @Then("I should see the product form")
    public void i_should_see_the_product_form(){
        assertEquals(true, product.isProductForm());
    }
    /*-----------------------------------------------------------------------------*/
    /*        SCENARIO: Successfully navigate to the product form   | END          */
    /*-----------------------------------------------------------------------------*/




    /*-----------------------------------------------------------------------------*/
    /*        SCENARIO: Successfully create a product                | START       */
    /*-----------------------------------------------------------------------------*/
    @When("I fill in the name with {string}")
    public void i_fill_in_the_name_with(String name) {
        product.fillName(name);
    }
    @When("I fill in the description with {string}")
    public void i_fill_in_the_description_with(String description) {
        product.fillDescription(description);
    }
    @When("I fill in the stock with {string}")
    public void i_fill_in_the_stock_with(String stock) {
        product.fillStock(stock);
    }
    @When("I fill in the warehouse with an existing warehouse id {string}")
    public void i_fill_in_the_warehouse_with_an_existing_warehouse_id(String warehouseID) {
        product.fillWarehouse(warehouseID);
    }
    @And("I click the submit button")
    public void i_click_the_submit_button(){
        product.submitForm();
    }
    @Then("my new product should be created")
    public void my_new_product_should_be_created(){
        // After a new product is successfully created the rows should increase by 1.
        Assert.assertEquals((rowsBeforeChangesProduct + 1),product.getRows());
    }
    /*-----------------------------------------------------------------------------*/
    /*        SCENARIO: Successfully create a product                | END         */
    /*-----------------------------------------------------------------------------*/

   


    /*-----------------------------------------------------------------------------*/
    /*        SCENARIO: Successfully delete a product                | START       */
    /*-----------------------------------------------------------------------------*/
    @Given("there exist at least 1 product in the table")
    public void there_exist_at_least_1_product_in_the_table(){
        rowsBeforeChangesProduct = product.getRows();
        if (rowsBeforeChangesProduct > 0)
            System.out.println("There does exist at least 1 product.\n");
        else
            System.out.print("Nothing to delete.\n");
    }

    @When("I double click the delete button")
    public void i_double_click_the_delete_button(){
        if (rowsBeforeChangesProduct > 0)
            product.clickDeleteButton1();
        else
            System.out.print("Nothing to delete.\n");
    }

    @Then("the product should be deleted")
    public void the_product_should_be_deleted(){
        if (rowsBeforeChangesProduct > 0){
            Assert.assertEquals(rowsBeforeChangesProduct - 1, product.getRows());
           System.out.println("Rows before changes : " + rowsBeforeChangesProduct  +
           "\nRows after : " + product.getRows());}
        else
            System.out.print("Nothing to delete.\n");
    }
   /*-----------------------------------------------------------------------------*/
   /*        SCENARIO: Successfully delete a product                | END         */
   /*-----------------------------------------------------------------------------*/




    /*-----------------------------------------------------------------------------*/
    /*        SCENARIO: Successfully edit a product                  | START       */
    /*-----------------------------------------------------------------------------*/

    @Given("there is at least one product on the table")
    public void there_is_at_least_one_product_on_the_table() {
        // We cannot edit a row if none exist.
        Assert.assertTrue(product.isRowsGreaterThanZero());    
    }

    @When("I click the edit icon button in a row")
    public void i_click_the_edit_icon_button_in_a_row() {
    product.clickEditButton();
    }
    @Then("I should see the edit form")
    public void i_should_see_the_edit_form() {
    Assert.assertTrue(product.isEditForm());
    }
    @When("I fill in the  edit name with {string}")
    public void i_fill_in_the_edit_name_with(String name) {
    product.editFillName(name);
    }
    @When("I fill in the  edit description with {string}")
    public void i_fill_in_the_edit_description_with(String description) {
    product.editFillDescription(description);
    }
    @When("I fill in the edit stock with {string}")
    public void i_fill_in_the_edit_stock_with(String stock) {
    product.editFillStock(stock);
    }
    @When("I fill in the edit warehouse with an existing warehouse id {string}")
    public void i_fill_in_the_edit_warehouse_with_an_existing_warehouse_id(String warehouse) {
    product.editWarehouse(warehouse);
    }
    @When("I click the edit submit button")
    public void i_click_the_edit_submit_button() {
    product.editFormSubmitButtonClick();
    }
    @Then("my selected product should be edited")
    public void my_selected_product_should_be_edited() {
    //Assert.assertEquals(rowsBeforeChanges, product.getRows());
    }

    /*-----------------------------------------------------------------------------*/
    /*        SCENARIO: Successfully edit a product                  | END         */
    /*-----------------------------------------------------------------------------*/

    @After("@product")
    public void teardown(){
        if (this.driver != null) {
            product.quitDriver();
        }
        System.out.println("--------- SCENARIO COMPLETE -----------");
    }

}