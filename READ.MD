
# Mixmax assessment by Maya Guraspashvily

The main programming language for this project was Java.

I used **Maven** as my build-in automation tool as it has a certain folder structure that I can utilise.
Within the `pom.xml` I've stored all the dependencies of this framework, which are:

	1. junit-jupiter version 5.9.2
	2. junit-jupiter-api version 5.9.2
	3. rest-assured version 5.3.0
	4. jackson-databind version 2.14.2
	5. lombok version 1.18.24

The structure of this project is simple:
* package petStore were all the methods were done
* package POJO for serialization purposes
* utilities for configurationReader and testBase
* .properties file to avoid hardcoding


The goal of this assessment is to implement REST API automated tests for a service of my choice.

Service of choice:  https://petstore.swagger.io/

`BaseUrL : https://petstore.swagger.io/v2`

Within this project several endpoints were used.
1. POST `/pet`
   Purpose of this endpoint to add a new pet to the store.
   Status codes:
   * `200` - ok
   * `405` - Invalid input

Request body:
```json
		{
			  "id": 0,
			  "category": {
			    "id": 0,
			    "name": "string"
			  },
			  "name": "doggie",
			  "photoUrls": [
			    "string"
			  ],
			  "tags": [
			    {
			      "id": 0,
			      "name": "string"
			    }
			  ],
			  "status": "available"
		}		
```
Response body:
```json
	{
		  "id": 9223372036854252000,
		  "category": {
		    "id": 0,
		    "name": "string"
		  },
		  "name": "doggie",
		  "photoUrls": [
		    "string"
		  ],
		  "tags": [
		    {
		      "id": 0,
		      "name": "string"
		    }
		  ],
		  "status": "available"
	}

```
2. PUT `/pet`
Purpose of this endpoint to update an existing pet
   Status codes:
   * `200` - ok
   * `400` - invalid ID supplied
   * `404` - pet not found
   * `405` - validation exception

Request body
```json
    	{
		  "id": 0,
		  "category": {
		    "id": 0,
		    "name": "string"
		  },
		  "name": "doggie",
		  "photoUrls": [
		    "string"
		  ],
		  "tags": [
		    {
		      "id": 0,
		      "name": "string"
		    }
		  ],
		  "status": "available"
		}
```
	
 	
Response body
```json
		{
		  "id": 9223372036854252000,
		  "category": {
		    "id": 0,
		    "name": "string"
		  },
		  "name": "doggie",
		  "photoUrls": [
		    "string"
		  ],
		  "tags": [
		    {
		      "id": 0,
		      "name": "string"
		    }
		  ],
		  "status": "available"
		}
```

3. GET `/pet/{petId}`
Purpose of this endpoint to return a single pet by id 
Status codes:

   * `200` - ok
   * `400` - invalid ID supplied	
   * `404` - pet not found	

Requires an id parameter 

Response body
```json
		{
		  "id": 2,
		  "category": {
		    "id": 1232,
		    "name": ""
		  },
		  "name": "CatTest",
		  "photoUrls": [],
		  "tags": [
		    {
		      "id": 2,
		      "name": ""
		    }
		  ],
		  "status": "available"
		}
```

Authentication
For this sample, you can use the api key `special-key` to test the authorization filters.

Test Cases:

GET:

```text
Given content type is json
When user sends a get request to /pet/{id}
And provides a valid id
Then status code is 200
And response type is json
And response payload values match the following:
id is provided id
```

```text
Given content type is json
When user sends a get request to /pet/{id}
And provides an invalid id
Then status code is 404
```

POST:

```text
Given I have the payload for creating a new pet
When I send a POST request to the /pet endpoint with the payload
Then status code is 200
And response payload values match the following:
    1. name - Fluffy
    2. status - available
```

PUT:
```text
Given I have the payload for creating a new pet
When I send a PUT request to the /pet endpoint with the payload
Then status code is 200
And response payload values match the following:
     1. name - Pikachu
     2. status - Sold
```

r
   2. cream
   3. sun protection
   ````


The Scenarios can be accessed within the features directory.
The code implementation can be accessed within the step definitions package.

`CI/CD`

Within this framework I added only 2 scenarios to the smoke suite.
As we all know smoke testing help us understand that the application is stable and the 
environment is up and running.
One of the criteria that has been used to identify which scenario to add to the smoke suite
is `critical basic functionality`. In our case:
1. login feature
2. basic search feature

I would also add the checkout , but based on the fact that we need to provide
a lot of private information to make the payment this scenario was omitted

















