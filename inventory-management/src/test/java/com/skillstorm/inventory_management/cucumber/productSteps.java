package com.skillstorm.inventory_management.cucumber;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class productSteps {
    
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

}
