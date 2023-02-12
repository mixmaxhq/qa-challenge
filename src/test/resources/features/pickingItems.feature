Feature: User should be able to put any item to the cart
  User Story:
  As a user i should be able to pick any item from the website

  Acceptance Criteria
  1. User should be able to pick 3 items and add them to the cart
  2. User should be able to remove any item from the cart
  3. User should be able to proceed to checkout

  Background: user logged in successfully
    Given user logged in successfully

  @smoke
  Scenario Template: User can add any item to the cart
    When user writes in the search bar "<item>"
    And user add them to the cart
    Then verify the item were added to the cart

    Examples:
      | item           |
      | cleanser       |
      | face cream     |
      | sun protection |


  Scenario: User can remove an item from the cart
    When  user enters the cart
    And user click the remove button
    Then verify the item was removed from the cart


  Scenario: User can proceed to checkout
    When user is in the cart
    And user clicks checkout
    Then verify user is redirected to checkout page