Feature: User login feature
  User Story:
  As a user i should be able to login with correct credentials

  Acceptance Criteria
  User should be able to login with valid credentials

  @smoke
  Scenario: User can login with valid credentials
    When user enters valid username
    And user enters valid password
    Then verify account icon has Hello username
