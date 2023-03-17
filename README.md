### Installation
Simply input the following once cloned:

`npm install`

Run Tests commands:
- UI head mode: `npm run ui-head`
- UI headless mode: `npm run ui`
- API head mode: `npm run api-head`
- API headless mode: `npm run api`

### CICD
- scripts are modified and ready to run on a CI server like Azure DevOps we need to modify the yaml file to run the scripts and add junit task to publish results. 

### Test Automation plan
- POM
- Framework :Hybrid framework >> Keyword driven + Data driven + Modular 

UI test Automation :
- Are a mix of E2E and modular based automation.
- E2E to target automation flows.
- Modular to test happy and unhappy scenarios for a specific module.

API Test Automation :
- CRUD operations => 
** Create user
** Authenticate user
** Add books to user account
** Update books
** Delete books

High-level test cases
- login > valid credentials, wrong email, wrong password, blank email, blank password
- Signup > sign up with new emial, with an already used email 
- products > finding an element using 3 criterias search by name, finding required product form sub-category and filtering products by color
- e2e > E2E flow for a user to >> sign in >> search for a product >> add to cart >> add address >> confim purchase  

Bug
- Description : When searching for a dress, T-shirt and Blouse are returned as well.

- priority : 1
- severity : medium (Search module is ver important and everyone is using it, but the returned results are kinda related, they are all women products so i wouldn't say it's high but needs to be raised)

- steps to reproduce the bug
-- go to http://automationpractice.pl/index.php
-- Enter 'Dress' in search bar and click enter
-- Check the returned items
-- Expected >> only dress items are returned  , 
-- Actual Results >> other categories are returned as well 




