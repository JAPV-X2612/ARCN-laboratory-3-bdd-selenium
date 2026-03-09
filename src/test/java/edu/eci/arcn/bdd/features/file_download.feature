Feature: File Download

  Scenario: Download a file from the file download page
    Given I am on the file download page
    When I click to download the file "sample.txt"
    Then the file "sample.txt" should exist in the downloads folder