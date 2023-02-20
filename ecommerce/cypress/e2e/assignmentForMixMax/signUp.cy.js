/// <reference types="cypress" />
import selectors from "../../support/selectors";
describe("Test Contact Us form via Automation Test Store", () => {
  beforeEach(() => {
    cy.navigateToAutomationTestStore();
  });
  const streetNames = [
    "Happy",
    "Sad",
    "Fine",
    "Terrible",
    "Good",
    "Exceptional",
    "Walnut",
  ];
  const streetTypes = [
    "Street",
    "Avenue",
    "Boulevard",
    "Lane",
    "Road",
    "Drive",
    "Court",
  ];
  const ukCities = [
    "London",
    "Birmingham",
    "Glasgow",
    "Liverpool",
    "Bristol",
    "Derby",
  ];
  const stateValue = "3519";
  const countryId = "United Kingdom";
  const succcessText = "Welcome to the Automation Test Store!";
  const urlText = "success";
  const newCustomerMessage = "I am a new customer.";
  const textMessageForContactUs = "Do you provide additional discount?";
  const responseMessage =
    "Your enquiry has been successfully sent to the store owner!";
  const ranndomEmail = generateRandomEmail();
  const ranndomLoginName = generateRandomLoginName();
  const randomFirtsName = generateRandomFirstName();
  const randomLastName = generateRandomLastName();
  const ranndomTelephoneNumber = generateRandomTelephoneNumber();
  const ranndomFaxNumber = generateRandomFaxNumber();
  const randomCompanyName = generateRandomCompanyName();
  const ranndomAddress1 = generateRandomAddress1();
  const ranndomCity = generateRandomUKCity();
  const ranndomPostCode = generateRandomUKPostcode();
  const ranndomPassword = generateRandomPassword();
  function generateRandomEmail() {
    const username = Math.random().toString(36).substring(2, 15);
    const domain = "example.com";
    return `${username}@${domain}`;
  }

  function generateRandomLoginName() {
    const loginName = Math.random().toString(36).substring(2, 15);
    return loginName;
  }
  function generateRandomFirstName() {
    const firstName = Math.random().toString(36).substring(2, 15);
    return firstName;
  }

  function generateRandomLastName() {
    const lastName = Math.random().toString(36).substring(2, 15);
    return lastName;
  }
  function generateRandomCompanyName() {
    const companyName = Math.random().toString(36).substring(2, 15);
    return companyName;
  }

  function generateRandomAddress1() {
    const streetNumber = Math.floor(Math.random() * 1000) + 1;
    const streetName =
      streetNames[Math.floor(Math.random() * streetNames.length)];
    const streetType =
      streetTypes[Math.floor(Math.random() * streetTypes.length)];
    const city = "Anytown";
    const state = "CA";
    const zipCode = Math.floor(Math.random() * (99999 - 10000 + 1)) + 10000; // Generates a random 5-digit number between 10000 and 99999
    return `${streetNumber} ${streetName} ${streetType}, ${city}, ${state} ${zipCode}`;
  }

  function generateRandomUKCity() {
    const randomIndex = Math.floor(Math.random() * ukCities.length);
    return ukCities[randomIndex];
  }

  function generateRandomUKPostcode() {
    const letters = "ABCDEFGHIJKLMNOPRSTUWYZ";
    const digits = "0123456789";
    const randomLetters =
      letters.charAt(Math.floor(Math.random() * letters.length)) +
      letters.charAt(Math.floor(Math.random() * letters.length));
    const randomDigits =
      digits.charAt(Math.floor(Math.random() * digits.length)) +
      digits.charAt(Math.floor(Math.random() * digits.length)) +
      digits.charAt(Math.floor(Math.random() * digits.length));
    return (
      randomLetters +
      randomDigits +
      " " +
      digits.charAt(Math.floor(Math.random() * digits.length)) +
      letters.charAt(Math.floor(Math.random() * letters.length)) +
      letters.charAt(Math.floor(Math.random() * letters.length))
    );
  }

  function generateRandomTelephoneNumber() {
    let phoneNumber = "555";
    for (let i = 0; i < 7; i++) {
      phoneNumber += Math.floor(Math.random() * 10);
    }
    return phoneNumber;
  }

  function generateRandomFaxNumber() {
    let faxNumber = "555";
    for (let i = 0; i < 7; i++) {
      faxNumber += Math.floor(Math.random() * 10);
    }
    return faxNumber;
  }

  function generateRandomPassword() {
    const minLength = 4;
    const maxLength = 20;
    const charset =
      "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789!@#$%^&*()_+~`|}{[]:;?><,./-=";
    let password = "";
    const passwordLength =
      Math.floor(Math.random() * (maxLength - minLength + 1)) + minLength;
    for (let i = 0; i < passwordLength; i++) {
      password += charset.charAt(Math.floor(Math.random() * charset.length));
    }
    return password;
  }

  it("Should be able to successfully sign up/register", () => {
    cy.get(selectors.account).click();
    cy.get(selectors.register).should("contain", newCustomerMessage);
    cy.get(selectors.continueAsNewCustomer).click();

    // Add personal information and continue

    cy.get(selectors.firstName).type(randomFirtsName);
    cy.get(selectors.lastName).type(randomLastName);
    cy.get(selectors.email).type(ranndomEmail);
    cy.get(selectors.telephoneNumber).type(ranndomTelephoneNumber);
    cy.get(selectors.faxNumber).type(ranndomFaxNumber);
    cy.get(selectors.companyName).type(randomCompanyName);
    cy.get(selectors.addressNumber1).type(ranndomAddress1);
    cy.get(selectors.addressNumber2).type("test3");
    cy.get(selectors.cityName).type(ranndomCity);

    cy.get(selectors.zoneId).select(stateValue);

    cy.get(selectors.postCode).type(ranndomPostCode);
    cy.get(selectors.countryId).select(countryId);
    cy.get(selectors.loginName).type(ranndomLoginName);
    cy.get(selectors.password).type(ranndomPassword);
    cy.get(selectors.confirmPassword).type(ranndomPassword);
    cy.get(selectors.newsletter1).click();
    cy.get(selectors.newsletter2).click();
    cy.get(selectors.agree).check();
    cy.get(selectors.continueAfterReadPrivacy).click();
    cy.get(selectors.subTextForVerification).should("contain", randomFirtsName);
  });

  it("Should be able to submit a successful submission via contact us form", () => {
    cy.get(selectors.firstNameContactForm).type(randomFirtsName);
    cy.get(selectors.emailForContactForm).type(ranndomEmail);
    cy.get(selectors.emailForContactForm).should("have.attr", "name", "email");

    cy.get(selectors.enquiryForContactForm).type(textMessageForContactUs);
    cy.get(selectors.submit).click();
    cy.get(selectors.responseMessageText).should("have.text", responseMessage);
  });
});
