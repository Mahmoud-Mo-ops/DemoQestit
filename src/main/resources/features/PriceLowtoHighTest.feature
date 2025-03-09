@PurchaseOrderFeature
Feature: verify the Sorting products depending on price (low to high price)
Background:
  Given I landed on Ecommerce

  @PositiveSortingTest
  Scenario Outline: Positive Test of Sorting Product depending on Price
    Given logged in with username <username> and password <password>
    Then Verify sorting by Price Low to High

   Examples: 
  | username      | password     | productName             | firstName | lastName | postalCode |
  | standard_user | secret_sauce | Sauce Labs Bolt T-Shirt | Mahmoud   | Goma     | 925        |
  | standard_user | secret_sauce | Sauce Labs Bolt T-Shirt | Mueller   | Mattius  | 15234      |
  | standard_user | secret_sauce | Sauce Labs Bolt T-Shirt | John      | Dohn     | 1153       |