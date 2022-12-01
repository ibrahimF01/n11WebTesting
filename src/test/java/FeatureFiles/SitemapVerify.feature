Feature: Sitemap Functionality
  Scenario: Sitemap Verify
    Given Navigate to NOnBir
    When Click on the Sitemap
    And Compare the titles with those in the section below
    Then Verify all subheadings that appear
