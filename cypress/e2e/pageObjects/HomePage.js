class HomePage{
    search(item){
        cy.get('#search_query_top').type(item)
        cy.get('.button-search').click()
    }

    clickElement(elementName){
        cy.contains(elementName).click()
    }
    getProductsName(){
        return cy.get('[itemprop="name"] a').each((product)=>{
            cy.wrap(product)
        })
    }

    
    sortProductsBy(value){
        cy.get('#selectProductSort').select(value)
    }

    selectProductByCategory(category,subCategory){
        cy.hoverThenClick(category,subCategory)
    }

    filterByColor(color){
        cy.contains(color).click()
        return cy.get('#color_16').invoke('attr','style')
    }

    buyProductByIndex(productNumber,address,city,state,postCode,phone){
        
        cy.get('[title="Add to cart"]').eq(productNumber-1).click()
        cy.get('.button-container > .button-medium > span').click()
        cy.contains('#cart_title','Shopping-cart summary')
        cy.get('[title="Proceed to checkout"]').eq(1).click()
        
        cy.get('[title="Update"]').eq(0).click()
        cy.get('#address1').clear().type(address)
        cy.get('#city').clear().type(city)
        cy.get('#id_state').select(state)
        cy.get('#postcode').clear().type(postCode)
        cy.get('#phone').clear().type(phone)
        cy.get('#alias').clear().type(address)
        cy.get('#submitAddress').click()
        cy.get('.cart_navigation > .button > span').click()
        cy.get('#cgv').check().should('be.checked')
        cy.get('.cart_navigation > .button > span').click()
        cy.get('[title="Pay by bank wire"]').click()
        cy.get('#cart_navigation > .button > span').click()
        return cy.get('.alert-success')
    }
}
export default HomePage