Feature: Covering th Pet section from the swagger page

  Scenario: As a owner, I like to add a pet profile and view the details

    Given I hit the pet-store and I add the Pet details "70","10","Dog","Beagle","img.jpg","0","petstoreapitest","available"
    Then The status code response is "200"
    When I search for the pet details with Pet-ID "70"
    Then The pet details are checked and the pet profile is "Present"

  Scenario: As a owner, I want to delete a pet profile and check whether it is deleted

    Given I hit the pet-store and I delete the pet details with Pet-ID "70"
    When I search for the pet details with Pet-ID "70"
    Then The pet details are checked and the pet profile is "Not Present"

  Scenario: As a owner, I want to Check whether error is thrown when I try to delete a pet profile with invalid pet id

    Given I hit the pet-store and I delete the pet details with Pet-ID " "
    Then The status code response is "405"

  Scenario: As a owner, When ID is not given for adding a pet profile error response is shown

    Given I hit the pet-store and I add the Pet details " ","10","cat","katza","img5.jpg","0","petstoreapitest","sold"
    Then The status code response is "400"