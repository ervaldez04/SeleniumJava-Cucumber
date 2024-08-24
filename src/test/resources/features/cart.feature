Feature: Add/Remove items in cart

  @Smoke @Regression
  Scenario: Add item in cart
    Given User is in site
    When User adds "Sauce Labs Fleece Jacket"
    Then "Sauce Labs Fleece Jacket" is added to cart
    And User removes "Sauce Labs Fleece Jacket"

  @Regression
  Scenario: Remove item in cart
    Given User is in site
    And User adds "Sauce Labs Fleece Jacket"
    When User removes "Sauce Labs Fleece Jacket"
    Then "Sauce Labs Fleece Jacket" is removed to cart