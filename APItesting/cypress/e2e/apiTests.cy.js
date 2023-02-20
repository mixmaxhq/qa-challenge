//import "../support/commands";

describe("Petstore API", () => {
  let createdPetIds = [];
  const maxRandomId = 100;
  const minRandomId = 1;
  const randomId = Math.floor(Math.random() * maxRandomId) + minRandomId;
  const petNames = [
    "Dior",
    "Channel",
    "Luna",
    "Coco",
    "Mango",
    "Ginger",
    "Bello",
    "Chloe",
    "Parfume",
  ];
  const randomName = petNames[Math.floor(Math.random() * petNames.length)];
  const filterStatuses = ["available", "pending", "sold"];
  const randomStatus =
    filterStatuses[Math.floor(Math.random() * filterStatuses.length)];
  const maxNonExistentId = 100;
  const minNonExistentId = 1;

  it("should handle error response", () => {
    let nonExistentId =
      Math.floor(Math.random() * maxNonExistentId) + minNonExistentId;

    // the best practice is deleting (in beforeEach hook) all pets/data that has been created and then run test cases. But unfortunately we dont have this endpoint (pet/* for DELETE method) in swagger document, so I used below logic
    // check if nonExistentId is included in createdPetIds array
    if (createdPetIds.includes(nonExistentId)) {
      // if nonExistentId is included, generate a new one until it is not included
      while (createdPetIds.includes(nonExistentId)) {
        nonExistentId =
          Math.floor(Math.random() * maxNonExistentId) + minNonExistentId;
      }
    }
    // add the new nonExistentId to createdPetIds array
    createdPetIds.push(nonExistentId);
    cy.api({
      method: "POST",
      url: "pet",
      body: {
        id: "invalidId",
        name: randomName,
        status: randomStatus,
      },
      failOnStatusCode: false,
    }).then((response) => {
      cy.log(response);
      const expectedStatusCode = 500;
      const expectedMessage = "something bad happened";
      expect(response.status).to.eq(expectedStatusCode);
      expect(response.body.message).to.contain(expectedMessage);
    });
  });

  it("should create a new pet", () => {
    cy.api({
      method: "POST",
      url: "pet",
      body: {
        id: randomId,
        name: randomName,
        status: randomStatus,
      },
    }).then((response) => {
      const expectedStatusCode = 200;
      expect(response.status).to.eq(expectedStatusCode);
      expect(response.body.id).to.eq(randomId);
      expect(response.body.name).to.eq(randomName);
      expect(response.body.status).to.eq(randomStatus);
    });
  });

  it("should retrieve a pet that does not exist", () => {
    const nonExistentId = randomId + 1;
    cy.api({
      method: "GET",
      url: `pet/${nonExistentId}`,
      headers: {
        accept: "application/json",
      },
      failOnStatusCode: false,
    }).then((response) => {
      expect(response.status).to.eq(404);
      expect(response.body.message).to.contain("Pet not found");
    });
  });

  it("should retrieve the created pet", () => {
    cy.api({
      method: "GET",
      url: `pet/${randomId}`,
      headers: {
        accept: "application/json",
      },
    }).then((response) => {
      expect(response.status).to.eq(200);
      expect(response.body.id).to.eq(randomId);
      expect(response.body.name).to.eq(randomName);
      expect(response.body.status).to.eq(randomStatus);
    });
  });

  it("should handle error response", () => {
    cy.api({
      method: "POST",
      url: "pet",
      body: {
        id: "invalidId",
        name: randomName,
        status: randomStatus,
      },
      failOnStatusCode: false,
    }).then((response) => {
      expect(response.status).to.eq(500);
      expect(response.body.message).to.contain("something bad happened");
    });

    cy.request({
      method: "POST",
      url: "pet",
      body: {
        id: randomId,
        name: randomName,
        status: randomStatus,
      },
    }).then((response) => {
      expect(response.status).to.eq(200);
      expect(response.body.id).to.eq(randomId);
      expect(response.body.name).to.eq(randomName);
      expect(response.body.status).to.eq(randomStatus);

      const updatedName = "Updated " + randomName;
      const updatedStatus = "sold";
      cy.api({
        method: "PUT",
        url: "pet",
        body: {
          id: randomId,
          name: updatedName,
          status: updatedStatus,
        },
      }).then((response) => {
        expect(response.status).to.eq(200);
        expect(response.body.id).to.eq(randomId);
        expect(response.body.name).to.eq(updatedName);
        expect(response.body.status).to.eq(updatedStatus);
      });
    });

    it("should delete the created pet", () => {
      cy.api({
        method: "DELETE",
        url: `pet/${randomId}`,
        headers: {
          accept: "application/json",
        },
        failOnStatusCode: false,
      }).then((response) => {
        if (response.status === 200) {
          expect(response.status).to.eq(200);
        } else {
          expect(response.status).to.eq(404);
          expect(response.body.message).to.contain("`pet/${randomId}`");
        }
      });
    });
  });
});
