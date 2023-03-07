# MixMax Test Automation Challenge

## ℹ️ About Project

This project is about creating test automation scenarios and building a framework from scratch for automation with recent technologies..
Tests can run in parallel and 2 different testing types which are API and WEB(ui) implemented. This framework also contains cross-browser ability.
Project is design with Page Object Model(POM).

## 💻 Using Technologies

- Java 17
- Docker
- Maven
- Selenium
- TestNG with Data Driven Testing
- RestAssured for Api Testing
- ExtentReport for reporting tests results
- Log4j for detailed logging
- 

## 🚀 How to use

- Install packages with `docker compose.yaml`
- Run `docker-compose up` to start the dockerized Dynamic selenium grid.
- If you want to run Only related <b>WEB-UI</b> test scenarios using mvn command: `mvn clean test -PWeb`
- If you want to run Only related <b>API</b> test scenarios using mvn command: `mvn clean test -PApi`
- OR you can run without maven command using  `apiTests.xml` and `uiTests.xml` files in IDE

## 🗒️ Additional Informations

This framework is flexible for any browsers. You can get detail information in below:

    . config.properties ---> GridUrl, BaseURl and Remote settings for framework initialization.
        . remote parameter is a boolean. if it is equal to true, tests will run in dynamic selenium grid containers. Else, tests will run in your local machine with related browser.

    . chrome.properties & firefox.properties --> contains browser capability settings (If you want to change or add capabilities, you can change in these properties. Empty file means no capabilities..)
    
    . ExtentReport is implemented in project for reporting with screeshots and detailed logs. In reports folder, you can access after each suite execution.

    . Login data & Some test data's (testData.xlsx) is stored in test-resources folder.

    . ScreenshotTaker class contains failed scenarios screenshot process. If you want to store screenshots as image file, you can use this class and stores screenshot folder in base project path. 

    . Log4j integrated with this framework to log in detailed.

    . In TestNG xml files(uiTests.xml) --> contains browser parameter for run test cases in different browsers.

    . Using MVN commands, you can create the Jenkins pipelines or any other CI tools. 

## 🕷️ Bugs & Faults

 - In API Automation:
   - `delayUser` testcase is failing when delay value set it up to 3. Because system returns response more than 3 seconds. It has to return response less than 3 sec.
   - `postUserWithWrongBody` testcase must be fail, but it is passing. Posting User information with Negative Testing technique, system accepting this data.
   
 - In Web Automation:
   - `problemUser` testcase is failing because, normally problem_user cannot log in to system, however system accepting its login process. 
