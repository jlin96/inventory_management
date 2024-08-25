@product
Feature: Product

Scenario: Successfully navigate to the product table 
    Given I am on the homepage
    When I select the product tab
    Then I should see the product table

Scenario: Successfully navigate to the product form
    Given I am on the product page
    When I click the 
