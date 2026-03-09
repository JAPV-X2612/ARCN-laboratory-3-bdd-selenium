Feature: Drag and Drop

  Scenario: Drag element A to element B position
    Given I am on the drag and drop page
    When I drag element A to element B
    Then element A should be in the position of element B