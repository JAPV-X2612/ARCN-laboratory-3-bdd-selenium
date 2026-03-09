Feature: Dynamic Loading

  Scenario: Wait for dynamically loaded element to appear
    Given I am on the dynamic loading page
    When I click the start button
    Then I should see the text "Hello World!"