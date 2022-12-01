Feature: Shopping Filter Control

  Background:
    Given Navigate to NOnBir
    And Login to the site
    Then User should login successfully

  Scenario: Shopping Filter Control Test
    Given Click on Bathroom & Home Appliances under the Home & Living menu
    And Sort by ascending price
    And Select required filters
    And Add product
    And Go to cart
    And Check shopping filters
    And Empty the cart