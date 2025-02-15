@LogOutFeature
Feature: Verify Log Out Feature
  As a user, I want to log out successfully
  so that I can secure my account.

Background:
  Given I landed on Ecommerce

@LogOutTest
Scenario Outline: TC005: Verify Log Out Functionality  
  Given I am logged in with username <username> and password <password>
  When I log out from the application  
  Then I should be redirected to the login page  

Examples: 
  | username      | password     |
  | standard_user | secret_sauce |
