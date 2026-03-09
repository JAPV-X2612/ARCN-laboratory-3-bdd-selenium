Feature: Login functionality on The Internet
  As a user
  I want to be able to log in to the application
  So that I can access the secure area

  Scenario Outline: Successful login with valid credentials
    Given the user is on the login page
    When the user enters username "<username>" and password "<password>"
    And the user clicks the login button
    Then the user should be redirected to the secure area
    And a success "You logged into a secure area!" should be displayed

    Examples:
      | username  | password               |
      | tomsmith  | SuperSecretPassword!   |