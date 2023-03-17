class SignupPage {

    Signup(email,sex,fName,lName,password,birthday,birthMonth,birthYear){
        cy.contains('.page-heading','Create an account')
        if(sex == 'male'){
            cy.get('#id_gender1').check().should('be.checked')
        }else{
            cy.get('#id_gender2').check().should('be.checked')
        }
        cy.get('#customer_firstname').type(fName)
        cy.get('#customer_lastname').type(lName)
        cy.get('#email').should('contain.value',email)
        cy.get('#passwd').type(password)
        cy.get('#days').select(birthday)
        cy.get('#months').select(birthMonth)
        cy.get('#years').select(birthYear)
        cy.get('#submitAccount').click()
        return cy.get('.alert-success')
    }
}
export default SignupPage