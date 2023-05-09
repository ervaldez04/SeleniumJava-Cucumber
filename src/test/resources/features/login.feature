Feature: Login functionality

  @Smoke @Regression
  Scenario: Successful login
    Given User is in SauceDemo Login Page
    When User enters "standard_user" and "secret_sauce"
    Then User is login successfully
    And User closes browser

  @Negative
  Scenario: Unsuccessful login
    Given User is in SauceDemo Login Page
    When User enters "standard_users" and "secret_sauce"
    Then Page will display "Epic sadface: Username and password do not match any user in this service"
    And User closes browser