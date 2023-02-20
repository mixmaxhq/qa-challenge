// ***********************************************
// This example commands.js shows you how to
// create various custom commands and overwrite
// existing commands.
//
// For more comprehensive examples of custom
// commands please read more here:
// https://on.cypress.io/custom-commands
// ***********************************************
//
//
// -- This is a parent command --
// Cypress.Commands.add('login', (email, password) => { ... })
//
//
// -- This is a child command --
// Cypress.Commands.add('drag', { prevSubject: 'element'}, (subject, options) => { ... })
//
//
// -- This is a dual command --
// Cypress.Commands.add('dismiss', { prevSubject: 'optional'}, (subject, options) => { ... })
//
//
// -- This will overwrite an existing command --
// Cypress.Commands.overwrite('visit', (originalFn, url, options) => { ... })

/*Cypress.Commands.add("createPet", (id, name, status) => {
    cy.request({
      method: "POST",
      url: "pet",
      body: {
        id,
        name,
        status
      }
    }).then((response) => {
      expect(response.status).to.eq(200);
      expect(response.body.id).to.eq(id);
      expect(response.body.name).to.eq(name);
      expect(response.body.status).to.eq(status);
    });
  });*/


  Cypress.Commands.add("createPet", () => {
    let retryCount = 0;
  
    // Define the route to intercept the request to create a new pet
    cy.server();
    cy.route({
      method: "POST",
      url: "/v2/pet",
    }).as("createPet");
  })