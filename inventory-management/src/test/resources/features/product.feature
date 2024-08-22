@product
Feature: Product

Scenario: Successfully navigate to the product form 
    Given I am on the homepage
    When I select the product tab
    Then I should see the product form
