describe('Login', () => {
  var randomstring = require("randomstring");
  const username = randomstring.generate(5);
  const email = username + '@automation.com';

  it('Sign up new user', () => {
    cy.visit('https://petstore.octoperf.com/actions/Catalog.action');
    
    cy.contains('Sign In').click();
    cy.contains('Register Now!').click();
    cy.get('[name="username"]').type(username);
    cy.get('[name="password"]').type(Cypress.env('password'), { log: false });
    cy.get('[name="repeatedPassword"]').type(Cypress.env('password'), { log: false });
    cy.get('[name="account.firstName"]').type('Test');
    cy.get('[name="account.lastName"]').type('Automation');

    cy.get('[name="account.email"]').type(email);
    cy.get('[name="account.phone"]').type('+356112233');
    cy.get('[name="account.address1"]').type('Test Address 1');
    cy.get('[name="account.address2"]').type('Test Address 2');
    cy.get('[name="account.city"]').type('City');
    cy.get('[name="account.state"]').type('State');
    cy.get('[name="account.zip"]').type('Zip');
    cy.get('[name="account.country"]').type('Country');

    cy.get('[name="newAccount"]').contains('Save Account Information').click();
    cy.contains('Saltwater');
  })

  it('Log in and search fish', () => {
    cy.visit('https://petstore.octoperf.com/actions/Catalog.action');
    
    cy.login(username, Cypress.env('password'));

    cy.get('[name="keyword"]').type('Angelfish');
    cy.get('[name="searchProducts"]').contains('Search').click();
    cy.contains('Salt Water fish from Australia');

    cy.get('[name="keyword"]').clear();
    cy.get('[name="keyword"]').type('Bulldog');
    cy.get('[name="searchProducts"]').contains('Search').click();
    cy.contains('Friendly dog from England');

    cy.get('[name="keyword"]').clear();
    cy.get('[name="keyword"]').type('Persian');
    cy.get('[name="searchProducts"]').contains('Search').click();
    cy.contains('Friendly house cat, doubles as a princess');
  })

  it('Add item to cart and proceed to checkout', () => {
    cy.visit('https://petstore.octoperf.com/actions/Catalog.action');

    cy.login(username, Cypress.env('password'));

    cy.get('#SidebarContent > a:nth-child(13) > img').click();
    cy.contains('AV-CB-01').click();
    cy.contains('Adult Male Amazon Parrot');
    cy.contains('Add to Cart').click();
    cy.contains('Proceed to Checkout').click();
    cy.contains('Payment Details');
    cy.get('[name="newOrder"]').contains('Continue').click();
    cy.contains('Please confirm the information below and then press continue...');
    cy.contains('Confirm').click();
    cy.contains('Thank you, your order has been submitted.');
  })

  it('Remove item from cart', () => {
    cy.visit('https://petstore.octoperf.com/actions/Catalog.action');
    
    cy.login(username, Cypress.env('password'));

    cy.get('#SidebarContent > a:nth-child(13) > img').click();
    cy.contains('AV-CB-01').click();
    cy.contains('Adult Male Amazon Parrot');
    cy.contains('Add to Cart').click();
    cy.contains('Remove').click();
    cy.contains('Your cart is empty.');
    cy.contains('Sub Total: $0.00');
  })
})