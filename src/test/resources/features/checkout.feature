Feature: Checkout

  Background: User is login to site
    Given User is login to SauceDemo site

  @Regression
  Scenario: Should be able to navigate to checkout when user adds an item
    Given User adds "Sauce Labs Onesie" item
    When User clicks Checkout
    Then User will be redirected to Checkout page

  @Smoke
  Scenario: Should be able to checkout item in cart
    Given User adds "Sauce Labs Onesie" item
    And User clicks Checkout
    When User enters "firstname", "lastname", "zip" information
    And User continues to checkout item
    Then Item is successfully checkout