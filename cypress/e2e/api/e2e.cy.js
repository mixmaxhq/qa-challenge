    describe('Verify adding and removing books ', function(){
      let token
      let userId
      let data
      
      before(()=>{
        cy.createStoreRandomUser()
        cy.fixture('bookstoreData.json').then((bookstoreData)=>{
        data = bookstoreData
        })

      })

      it('Verify creating new user', function(){      
          cy.request({
              method: 'POST',
              url: 'https://demoqa.com/Account/v1/User',
              body: data
          }).then(($response) => {
              expect($response.status).to.eq(201)
              expect($response.body.userID).to.exist
              expect($response.body.username).to.eq(data.userName)
              userId = $response.body.userID
          })
      })

      it('Verify generating new Token', () => {
        cy.request({
          method: 'POST',
          url: 'https://demoqa.com/Account/v1/GenerateToken',
          body: data
        }).then(($response) => {
          expect($response.status).to.eq(200)
          expect($response.body.token).to.exist
          expect($response.body.status).to.eq('Success')
          token = $response.body.token
        })
      })

    it('Verify adding a book to the user', () => {
          cy.request({
            method: 'POST',
            url: "https://demoqa.com/BookStore/v1/Books",
            headers: {Authorization: 'Bearer ' + token},
            body: {
              userId,
              collectionOfIsbns: data.collectionOfIsbns
            },
          }).then(($response) => {
            expect($response.status).to.eq(201)
            expect($response.body.books[0].isbn).to.eq(data.collectionOfIsbns[0].isbn)
          })
        })

        it('Verify updating book', () => {
          cy.request({
            method: 'PUT',
            url: `https://demoqa.com/BookStore/v1/Books/${data.collectionOfIsbns[0].isbn}`,
            headers: {Authorization: 'Bearer ' + token,},
            body: {
              userId,
              isbn :data.collectionOfIsbns[1].isbn
            },
          }).then(($response) => {
            expect($response.status).to.eq(200)
          })
        })

        it('Verify removing book from the user', () => {
          cy.request({
            method: 'DELETE',
            url: "https://demoqa.com/BookStore/v1/Book",
            headers: {Authorization: 'Bearer ' + token,},
            body: {
              userId,
              isbn :data.collectionOfIsbns[1].isbn
            },
          }).then(($response) => {
            expect($response.status).to.eq(204)
          })
        })


        //unhappy flow
  
    it('Verify only availble books can be added to the user', () => {
      cy.request({
        method: 'POST',
        url: "https://demoqa.com/BookStore/v1/Books",
        failOnStatusCode: false,
        headers: {Authorization: 'Bearer ' + token},
        body: {
          userId,
          collectionOfIsbns: [{ isbn: '1111111111' }]
        },
      }).then(($response) => {
        expect($response.status).to.eq(400)
        expect($response.body.message).to.eq("ISBN supplied is not available in Books Collection!")
      })
    })

    it('Verify it is not allowed to remove a book that is not pre-added', () => {
          cy.request({
            method: 'DELETE',
            url: "https://demoqa.com/BookStore/v1/Book",
            failOnStatusCode: false,
            headers: {Authorization: 'Bearer ' + token},
            body: {
              userId,
              isbn :'9781449444411' 
            },
          }).then(($response) => {
            expect($response.status).to.eq(400)
            expect($response.body.message).to.eq("ISBN supplied is not available in User's Collection!")
          })
        })

      })







