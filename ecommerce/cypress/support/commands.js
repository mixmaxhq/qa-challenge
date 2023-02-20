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

const loginNameForSignIn = "#loginFrm_loginname";
const passwordForSignIn = "#loginFrm_password";
const loginBtnForSignIn = "#loginFrm > fieldset > .btn";
const subTextForSignIn = ".subtext";
const accountBtn =
  '#main_menu_top > [data-id="menu_account"] > .top > .menu_text';
const loginName = "rwd4ttzah5k";
const password = "/:{pbPxx;kU><tA+";
const firstName = "9lf89vyu4kl";
const getContact = "a[href$='contact']";

Cypress.Commands.add("navigateToAutomationTestStore", () => {
  cy.visit("/");
  cy.get(getContact).click();
});
Cypress.Commands.add("navigateToContactUsPage", () => {
  cy.visit("/" + "index.php?rt=account/create");
  cy.get(getContact).click();
});
Cypress.Commands.add("navigateToSignInToPage", () => {

  cy.get(accountBtn).click();
  cy.get(loginNameForSignIn).type(loginName);
  cy.get(passwordForSignIn).type(password);
  cy.get(loginBtnForSignIn).click();
  cy.get(subTextForSignIn).should("contain", firstName);
});

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
