@product
Feature: Product

  Scenario: Successfully navigate to the product table
    Given I am on the homepage
    When I select the product tab
    Then I should see the product table

  Scenario: Successfully navigate to the product form
    Given I am on the product page
    When I click the Add Product button
    Then I should see the product form

  Scenario: Successfully create a product
    Given I am on the product form
    When I fill in the name
    And I fill in the description
    And I fill in the stock
    And I fill in the warehouse with an existing warehouse id
    And i click the submit button
    Then my new product should be created

  #Scenario: Successfully delete a product
  #  Given I am on the product page
  #  When I double click the delete button
  #  Then the product should be deleted
