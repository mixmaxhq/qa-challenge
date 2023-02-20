/// <reference types="cypress" />
import selectors from "../../support/selectors";

describe("Test shopping cart in Automation Test Store", () => {
  beforeEach(() => {

    /*cy.fixture('user').then((data)=>{
      this.data=data
  })*/
    cy.navigateToAutomationTestStore();
    cy.navigateToSignInToPage();
  });

  const succcessText = "Welcome to the Automation Test Store!";
  const urlText = "success";


  it("validate specific hair care product", () => {
    cy.get(selectors.hairCare).contains("Hair Care").click();
    cy.get(selectors.headerText).then(($headerText) => {
      const headerText = $headerText.text();
      cy.log("Found header text: " + headerText);
    });
    cy.get(selectors.productName)
      .eq(0)
      .invoke("text")
      .as("productThumbnail");
    cy.get("@productThumbnail").its("length").should("be.gt", 5);
    cy.get("@productThumbnail").should("include", "Seaweed Conditioner");
  });

  it("Should be able to add and remove products, and proceed to checkout", () => {
    // Navigate to Hair Care -> Conditioner -> Seaweed Conditioner

    cy.get(selectors.category1).contains("Hair Care").click();
    cy.get(selectors.conditioner).contains("Conditioner").click();

    cy.get(selectors.seaweedConditioner).click();

    // Add 2 items to cart
    cy.get(selectors.productQuanttity1).clear().type("2");
    cy.get(selectors.cart).click();

    // Remove item from cart
    cy.get(selectors.remove).click({multiple:true});

    // Navigate to Books -> Audio Books
    cy.get(selectors.book).contains("Books").click();
    cy.get(selectors.paperBackItem).click();

    // Add item to cart
    cy.get(selectors.product).click();
    cy.get(selectors.productQuanttity1).clear().type("1");
    cy.get(selectors.cart).click();
    // Proceed to checkout
    cy.get(selectors.cartCheckout).click();
    cy.url().should("include", "checkout");
    cy.get(selectors.checkout).click();
    cy.url().should("contain", urlText);
    cy.get(selectors.continue).click();
    cy.get(selectors.welcomeMessage).should("contain", succcessText);
  });
});
