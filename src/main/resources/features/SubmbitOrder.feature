@PurchaseOrderFeature
Feature: Purchase the order from Ecommerce Website
  As a user, I want to be able to purchase products from the e-commerce website successfully.

Background:
  Given I landed on Ecommerce

@PositivePurchaseTest
Scenario Outline: Positive Test of Submitting the order
  Given I am logged in with username <username> and password <password>
  When I add a product <productName> to the cart
  And I proceed to the cart page, proceed with checkout, and click on the checkout button
  And I fill the billing information on the checkout page with First Name <firstName>, Last Name <lastName>, and Postal Code <postalCode>
  And I complete the order on the checkout overview page by clicking on the finish button
  Then "Thank you for your order!" message is displayed on the confirmation page

Examples: 
  | username      | password     | productName             | firstName | lastName | postalCode |
  | standard_user | secret_sauce | Sauce Labs Bolt T-Shirt | Mahmoud   | Goma     | 925        |
  | standard_user | secret_sauce | Sauce Labs Bolt T-Shirt | Mueller   | Mattius  | 15234      |
  | standard_user | secret_sauce | Sauce Labs Bolt T-Shirt | John      | Dohn     | 1153       |
