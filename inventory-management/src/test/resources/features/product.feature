@product
Feature: Product
Description: The purpose of this feature is to test product CRUD functionality

  Background: Successfully navigate to the product page
    Given I am on the homepage
    When I select the product tab
    Then I should see the product table

  Scenario: Successfully navigate to the product form
    When I click the add product button
    Then I should see the product form

  Scenario Outline: Successfully create a product
    When I click the add product button
    * I fill in the name with "<name>"
    * I fill in the description with "<description>"
    * I fill in the stock with "<stock>"
    * I fill in the warehouse with an existing warehouse id "<warehouseID>"
    * I click the submit button
    Then my new product should be created

    Examples:
    |name               | description           | stock | warehouseID |
    |SONY Stereo System | Home Entertainement   |  100  |     65      |
   # |XBOX Series X      | Home Entertainement   |  100  |     65      |
   # |Shredder           | Office Supply         |   50  |     65      |

  Scenario: Successfully delete a product
    Given there exist at least 1 product in the table
    When I double click the delete button
    Then the product should be deleted

  Scenario Outline: Successfully edit an existing product
    Given there is at least one product on the table
    When I click the edit icon button in a row 
    Then I should see the edit form 
    When I fill in the  edit name with "<name>"
    * I fill in the  edit description with "<description>"
    * I fill in the edit stock with "<stock>"
    * I fill in the edit warehouse with an existing warehouse id "<warehouseID>"
    * I click the edit submit button
    Then my selected product should be edited

    Examples:
    |name               | description           | stock | warehouseID |
    |edit name          | edit description      |  100  |     1       |
