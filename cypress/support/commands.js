/// <reference types="Cypress" />
import {faker} from '@faker-js/faker'

Cypress.Commands.add('createRandomUser',()=>{
    const email = faker.internet.exampleEmail()
    const sex = faker.name.sex()
    const firstName = faker.name.firstName()
    const lastName = faker.name.lastName()
    const password = faker.internet.password() 
    const birthDay = faker.datatype.number({'min': 1,'max': 27})
    const birthMonth = faker.date.month()
    const address = faker.address.streetAddress()
    const city = faker.address.city()
    const state = faker.address.state()
    const postalCode = faker.address.zipCode()
    const mobileNumber = faker.phone.number()
    const birthyear = (faker.datatype.number({'min': 1990,'max': 2002})).toString()
    const user = {email,sex,firstName,lastName,password,birthDay,birthMonth,birthyear,
    address,city,state,postalCode,mobileNumber}
    cy.writeFile("./cypress/fixtures/newUser.json", user)
})

Cypress.Commands.add('createStoreRandomUser',()=>{
    const userName = faker.internet.userName()
    const password = faker.internet.password() + '@1' 

    const data = {userName,password,"collectionOfIsbns": [{"isbn": "9781593275846"},
    { isbn: '9781449337711' }]
    }
    cy.writeFile("./cypress/fixtures/bookstoreData.json", data)
})

Cypress.Commands.add('hoverThenClick',(hoverOverElement,clickElement)=>{
    cy.get(`[title="${hoverOverElement}"]`).first().next().invoke('show')
    cy.contains(clickElement).click()
})


