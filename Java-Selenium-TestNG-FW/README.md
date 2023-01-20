**Overview**

Automation for  https://www.saucedemo.com/ website prioritizing the purchase flow.
1. Validate Login 
2. Add to cart
3. Remove from cart
4. Check out flow E2E

**Pre condition**
1. Intelliji IDE with Maven, selenium and testNG
2. JDK

**Technology used ** 
1. Maven
2. Java 
3. Selenium
4. TestNG
5. Extent report 

**Best practices** 
1. Uses Page object model 
2. Use Reusable Request
3. Uses test data from json file
4. Failure screenshots can be found in screenshot folder
5. The Project is CI/CD friendly. Jobs can be scheduled using the TestNG XML file
6. Report are generated and stored in report folder with the test suite name 

**Components** 
   Page objects are in the directory src/main/java/pages
   Test classes are in the directory src/test/java/tests
   Listener class is in the directory src/main/java/util
   Test data is defined in TestData.json file

**How to Run**
1. Clone the repository from the branch
2. Run the TestNGTestSuite.xml file in intellij OR
3. Run mvn test in intellj / terminal 


**Note :**
1. The detailed test flow and bug details can be found in _**QATask1 file**_
2. The project uses 3 different ways to access data just to show the variation
   a. Login credential details from Testng xml file (TestNGTestSuite.xml)
   b. Personal details for filling the form is fetched from Testadata.json  
   c. Data parameters for passing invalid user details for login validation

Future improvement 
1. Report can be made even more explanatory 
2. Edge testcases and more scenarios can be added

