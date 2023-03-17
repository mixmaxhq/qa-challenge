import SignupPage from '../pageObjects/SignupPage'
import HomePage  from '../pageObjects/HomePage'
import SinginPage from '../pageObjects/SigninPage'
const homePage = new HomePage()
const singinPage = new SinginPage()
const signupPage = new SignupPage()
let user

describe('Signup module', () => {
  before(()=>{
    cy.createRandomUser()
    cy.fixture('newUser.json').then((data)=>{
    user = data
    })
  })

  beforeEach(()=>{
    cy.visit('/')
  })
  it('Verify a successful signup', () => {
    homePage.clickElement('Sign in')
    singinPage.createAccount(user.email)
    signupPage.Signup(user.email,user.sex,
      user.firstName,user.lastName,
      user.password,user.birthDay,
      user.birthMonth,user.birthyear).should('include.text','Your account has been created.')
  
    })

  it('Verify unsuccessful signup with an already registred email', () => {
    homePage.clickElement('Sign in')
    singinPage.createAccount(user.email)
    singinPage.getAlertMsg().should('include.text','email address has already been registered')
  })
})