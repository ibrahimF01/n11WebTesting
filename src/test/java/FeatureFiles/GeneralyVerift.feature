
#enter the information update field and update the "name" section with a random name and verify.

Feature: Verify 
  Scenario: Verify that all the text – product, category name, price, and product description are clearly visible.
    
    Given Navigate to Neleven
    When I type toilet brush in the search box and click the search button
    And I click on any product
    Then Product title should look correct
    Then Product details should look correct
    Then Product price should look correct
    Then shipping price should appear


