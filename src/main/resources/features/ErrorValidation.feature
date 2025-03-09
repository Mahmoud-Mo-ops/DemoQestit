@PurchaseOrderFeature
Feature: verify the error message when log in with invalid credentials
Background:
  Given I landed on Ecommerce

  @NegativeLoginTest
  Scenario Outline: Negative Test of Log In
    Given logged in with username <username> and password <password>
    Then I should see an error message "<errorMessage>"

    Examples: 
      | username      | password  | errorMessage                             |
      | standard_user | secret_   | Epic sadface: Epic sadface: Password is required|
