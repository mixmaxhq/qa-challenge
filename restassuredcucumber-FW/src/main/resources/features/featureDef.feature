Feature: Covering th Pet section from the swagger page

  Scenario Outline: As an owner, I like to add a pet profile and view the details

    Given I hit the pet-store and I add the Pet details "<ID>","<categoryId>","<categoryName>","<petName>","<imgURL>","<tagsId>","<tagsName>","<petStatus>"
    Then The status code of the action is "<Success>"
    When I search for the pet details with Pet-ID "<ID>"
    Then The pet details are checked and the pet profile is "<Present>"
    And The details are validated against the added "<categoryName>" and "<petName>"

    Examples:
      | ID | Success | categoryId | categoryName | petName | imgURL  | tagsId | tagsName    | petStatus | Present |
      | 90 | 200        | 10         | Dog          | Beagle  | img.jpg | 50      | petStoreApi | available | 200     |

  Scenario Outline: As an owner, I want to delete a pet profile and check whether it is deleted

    Given I hit the pet-store and I delete the pet details with Pet-ID "<ID>"
    When I search for the pet details with Pet-ID "<ID>"
    Then The pet details are checked and the pet profile is "<NOT PRESENT>"

    Examples:
      | ID |  NOT PRESENT |
      | 70 | 404         |

  Scenario Outline: As an owner, I want to Check whether “405” error message is thrown, when I try to delete a pet profile with invalid pet id

    Given I hit the pet-store and I delete the pet details with Pet-ID "<ID>"
    Then The status code of the action is "<INVALID INPUT>"

    Examples:
      | ID | INVALID INPUT|
      |    | 405        |

  Scenario Outline: As an owner, I want to Check whether “400” error message is thrown, When ID is empty for adding a pet profile

    Given I hit the pet-store and I add the Pet details "<ID>","<categoryId>","<categoryName>","<petName>","<imgURL>","<tagsId>","<tagsName>","<petStatus>"
    Then The status code of the action is "<INVALID ID>"

    Examples:
      | ID | INVALID ID | categoryId | categoryName | petName | imgURL   | tagsId | tagsName    | petStatus |
      |    | 400        | 10         | Cat          | kastze  | img3.jpg | 0      | petStoreApi | Sold      |

  Scenario Outline: As an owner, I want to update a pet’s profile
    Given I hit the pet-store and I add the Pet details "<ID>","<categoryId>","<categoryName>","<petName>","<imgURL>","<tagsId>","<tagsName>","<status>"
    And I update the pet status to "<petStatus>" for Pet "<ID>","<categoryId>","<categoryName>","<petName>","<imgURL>","<tagsId>","<tagsName>"
    Then The status code of the action is "<Success>"

    Examples:
      | ID | Success | categoryId | categoryName | petName | imgURL   | tagsId | tagsName    | status|petStatus |
      | 65 | 200        | 10         | Cat          | kastze  | img3.jpg | 0      | petStoreApi | Available   |Sold      |
