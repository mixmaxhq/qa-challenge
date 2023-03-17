class SinginPage {
    
    signin(email,password){
        cy.get('#email').type(email)
        cy.get('#passwd').type(password)
        cy.get('#SubmitLogin').click()
    }
    
    
    
    //feh constractor fe js?
    createAccount(email)
    {
        cy.contains('.page-heading','Authentication')
        cy.get('#email_create').type(email)
        cy.get('#SubmitCreate').click()
    }

    getAlertMsg(){
        //this is a general locator for either entering wrong password 
        //or registering with and existing account
       return cy.get('.alert-danger li')
    }

    getWelcomeMsg(){
        return cy.get('.info-account')
    }
}
export default SinginPage