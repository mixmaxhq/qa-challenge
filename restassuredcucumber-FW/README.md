**Overview**

Application used : https://petstore.swagger.io/
API capability : Pet profile (Pet Everything about your Pets)

CRUD functionalities covered 
1. POST - Add a new pet to the store :  /pet
2. GET -  Find pet by ID : /pet/{petId}
3. PUT - Update an existing pet: /pet
4. Delete - Deletes a pet : /pet/{petId}

HTTP responses used :
200 - successful operation
400 - Invalid ID supplied
404 - Pet not found
405 - Invalid input

_Note:_ Test scenario with detailed operation and response coverage can be viewed in "QATask2" pdf 

**Pre condition**
1. Intellij IDE with Maven
2. Cucumber Plugin
3. JDK

**Technology used**
1. Maven
2. Junit
3. Rest assured
4. Cucumber report

**Best practices**
1. Uses Rest Assure and Maven 
2. Use Page object model 
3. CI/CD friendly
4. Followed BDD approach (easy to understand )
5. Use Reusable RequestSpecifications and the test steps can be reused for multiple testcases 
6. For report : Cucumber report is used

**Components**
Feature is defined in the directory src/main/resources/features
Test steps/class is in the directory src/main/java/teststeps
Runner file is in src/test/java/runners

**How to Run**
1. Execute maven command - mvn test or
2. Run the runnerfile

**Future improvement**
1. Report storage while integrating in CI/CD with #publish test report in artifacts
2. More testcases can be added covering the other API capability 
3. POJO class can be implemented for Request or Response JSON

