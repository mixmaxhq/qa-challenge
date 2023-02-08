Cypress.Commands.add('login', (username, password) => 
{    
    cy.contains('Sign In').click();
    cy.get('[name="username"]').type(username);
    cy.get('[name="password"]').clear();
    cy.get('[name="password"]').type(password, { log: false })
    cy.get('[name="signon"]').contains('Login').click();
    cy.contains('Welcome Test!');
})