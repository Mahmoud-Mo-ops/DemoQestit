@CartManagementFeature
Feature: Verify removing items from the shopping cart
  As a user, I want to remove an item from the shopping cart
  so that I can modify my purchase before checkout.

Background:
  Given I landed on Ecommerce

@RemoveFromCartTest
Scenario Outline: Verify removing a product from the shopping cart
  Given I am logged in with username <username> and password <password>
  When I add the product <productName> to the cart
  And I navigate to the shopping cart
  And I remove the product <productName> from the cart
  Then the cart count should be 0

Examples: 
  | username      | password     | productName                  |
  | standard_user | secret_sauce | Sauce Labs Fleece Jacket    |
  | standard_user | secret_sauce | Sauce Labs Backpack         |
  | standard_user | secret_sauce | Sauce Labs Onesie           |
