import HomePage  from '../pageObjects/HomePage'
const homePage = new HomePage()
let user

describe('Finding products', () => {
  before(()=>{
    cy.fixture('existingUser.json').then((data)=>{
    user = data
    })
  })

  beforeEach(()=>{
    cy.visit('/')
  })
  it('Verify Searching for a product using search bar', () => {
    homePage.search('Printed Summer Dress')
    homePage.getProductsName().should('include.text','Dress')
  })

  it('Verify Searching for a product by filter by color', () => {
    homePage.clickElement('Women')
    homePage.filterByColor('Beige').should('eq','background:#f5f5dc;')
  })

  it('Verify Searching for a product by subcategory', () => {
    homePage.selectProductByCategory('Women','T-shirts')
    homePage.getProductsName().should('include.text','T-shirts')
})

})