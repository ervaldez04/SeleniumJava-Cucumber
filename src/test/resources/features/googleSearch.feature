Feature: Google Search

  @Smoke @Regression @Test
  Scenario: Should be able to search
    Given User navigates to Google site
    When User searches word
    Then Page will be redirected to the Search Page
