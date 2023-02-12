Feature: User login feature
  User Story:
  As a user i should be able to login with correct credentials

  Scenario: User can login with valid credentials
    When user enters valid username
    And user enters valid password
    Then verify account icon has Hello username
