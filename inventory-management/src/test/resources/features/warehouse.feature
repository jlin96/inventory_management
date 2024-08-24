@warehouse

Feature: Warehouse

    # Scenario: Clicking warehouse tab
    #     Given I am on the Home Tab
    #     When I click the Add Warehouse Button
    #     Then Add Warehouse Modal shows show up.

    Scenario: Clicking add warehouse button
        Given I am on the warehouse tab
        When I click the add warehouse button
        Then I should see the add warehouse modal

    #Scenario: Adding warehouse to table
    #    Given I am on the add warehouse modal
    #    When I submit the form
    #    Then I should see the warehouse show up on the table

    #Scenario: Clicking edit icon on a row
    #    Given I am on the warehouse tab
    #    When I click the edit icon on a warehouse row
    #    Then I should see the edit warehouse modal

    #Scenario: Editing a warehouse
    #    Given I am on the edit warehouse modal
    #    When I submit the form
    #    Then I should see the edited warehouse show up on the table

    # Scenario: Deleting a warehouse
    #     Given I am on the warehouse tab
    #     When I click the delete icon on a warehouse row
    #     Then I should not see the warehouse show up on the table