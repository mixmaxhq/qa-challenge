import HomePage  from '../pageObjects/HomePage'
import SinginPage from '../pageObjects/SigninPage'
const homePage = new HomePage()
const singinPage = new SinginPage()
let user

describe('E2E purchasing flows', () => {
  before(()=>{
    cy.createRandomUser()
    cy.fixture('existingUser.json').then((data)=>{
    user = data
    })
    cy.visit('/')
  })

  it('Verify a user can login and make a pruchase', () => {
    homePage.clickElement('Sign in')
    singinPage.signin(user.email,user.password)
    singinPage.getWelcomeMsg().should('include.text','Welcome to your account.')
    homePage.clickElement(user.firstName + ' ' +user.lastName)

    homePage.search('dress')

    homePage.buyProductByIndex(1,user.address,user.city,user.state,
        user.postalCode,user.mobileNumber).
        should('have.text','Your order on My Store is complete.')
  })
})