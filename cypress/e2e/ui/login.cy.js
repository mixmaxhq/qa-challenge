import HomePage  from '../pageObjects/HomePage'
import SinginPage from '../pageObjects/SigninPage'
const homePage = new HomePage()
const singinPage = new SinginPage()
let user

describe('Login module', () => {
  before(()=>{
    cy.fixture('existingUser.json').then((data)=>{
    user = data
    })
  })

  beforeEach(()=>{
    cy.visit('/')
  })
  it('Verify a successful login', () => {
    homePage.clickElement('Sign in')
    singinPage.signin(user.email,user.password)
    singinPage.getWelcomeMsg().should('include.text','Welcome to your account.')
    homePage.clickElement(user.firstName + ' ' +user.lastName)
  })

  it('Verify unsuccessful login using wrong password', () => {
    homePage.clickElement('Sign in')
    singinPage.signin(user.email,'wrongPassword')
    singinPage.getAlertMsg().should('include.text','Authentication failed.')
  })

  it('Verify unsuccessful login using wrong email', () => {
    homePage.clickElement('Sign in')
    singinPage.signin('wrongemail@asdwrongxcf.com',user.password)
    singinPage.getAlertMsg().should('include.text','Authentication failed.')
  })

  it('Verify unsuccessful login using email only', () => {
    homePage.clickElement('Sign in')
    singinPage.signin(user.email,' ')
    singinPage.getAlertMsg().should('include.text','Password is required.')
  })

  it('Verify unsuccessful login using password only', () => {
    homePage.clickElement('Sign in')
    singinPage.signin(' ',user.password)
    singinPage.getAlertMsg().should('include.text','An email address required.')
  })

})