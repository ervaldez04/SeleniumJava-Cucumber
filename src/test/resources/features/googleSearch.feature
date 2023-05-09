Feature: Google Search
  As a user
  I want to search Google
  So that I can find what I'm looking for

  @Smoke @Regression @Test
  Scenario: Should be able to search
    Given User navigates to Google site
    When User searches word
    Then Page will be redirected to the Search Page
