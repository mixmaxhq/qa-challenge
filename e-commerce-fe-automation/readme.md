# Cypress FE Automation
Cypress project for FE automation. It allows writing and running automation tests locally with real browser or in CLI with headless browser Electron.

## Folder structure
    cypress
       |
        e2e
            |
        fixtures
            |
        support
            |
            commands.js
            e2e.js
    cypress.config.js
    cypress.env.json
    package.json

- e2e folder is place where we write tests. Default file extension is .js, but the test files may be written as .js, .jsx, .coffee and .cjsx.
- fixtures folder is used to store our test data and avoid hard coded data in our test cases
- supoort folder is used for supporting writing tests
    - commands.js is the file where we add commonly used functions and call in our tests
    - e2e.js is the file which runs before every single spec file/test case. It is a great place to put global configuration and behaviors here
- cypress.config.js is the default place for Cypress configurations, report generation configuration etc.
- cypress.env.json is file where we add environment variables for our tests
- package.json contains metadata and required dependencies for running tests

## Cypress Install
- Clone the project locally 
- Run 'npm install'

## Run tests
There are few types of scripts for running tests, executing tests and generating report. Select execution with selecting browser manually, execution with preselected chrome browser or execution with preselected chrome browser, but in headless mode only on CLI.

### Run tests and select browser
- 'npm run cypress:test'

### Run tests with Chrome browser
- 'npm run cypress:test:chrome'

### Run tests with Chrome headless browser in CLI
- 'npm run cypress:test:cli'

## Reports generation
Reports are generated automatically when tests finished and are located under cypress/reports folder.

## Environment variables
Cypress using environment variables for running tests. To be able to run tests locally you need to create environment file:

### cypress.env.json
    {
        "password": "any_password"
    }