Feature: Login with Multiple Data

  @Regression
  Scenario Outline: Successful login using different valid credentials
    Given User is in SauceDemo Login Page
    When User enters <username> and <password>
    Then User is login successfully
    And User closes browser
    Examples:
      | username                | password     |
      | standard_user           | secret_sauce |
      | problem_user            | secret_sauce |
      | performance_glitch_user | secret_sauce |

  @Negative
  Scenario: Unsuccessful login using locked out credential
    Given User is in SauceDemo Login Page
    When User enters credentials
      | locked_out_user | secret_sauce |
    Then Page will display error message
      | Epic sadface: Sorry, this user has been locked out. |
    And User closes browser

  Scenario: Successful login using valid credential
    Given User is in SauceDemo Login Page
    When User enters credentials
      | standard_user | secret_sauce |
    Then User is login successfully
    And User closes browser

  @Regression @Negative
  Scenario Outline: Unsuccessful login using different invalid credentials
    Given User is in SauceDemo Login Page
    When User enters <username> and <password>
    Then Page will display "Epic sadface: Username and password do not match any user in this service"
    And User closes browser
    Examples:
      | username         | password      |
      | standard_users   | secret_sauce  |
      | standard_user    | secret_sauces |
      | standard_users   | secret_sauces |
