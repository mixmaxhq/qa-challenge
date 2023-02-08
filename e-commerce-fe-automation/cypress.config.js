const { defineConfig } = require("cypress");

module.exports = defineConfig({
  reporter: 'cypress-mochawesome-reporter',
  video: false,
  reporterOptions: {
    reportPageTitle: "Cypress Report",
    reportDir: "cypress/reports",
    reportFilename: "report",
    charts: true,
    html: true,
    json: true,
    overwrite: false,
    embeddedScreenshots: true,
    inlineAssets: true,
    debug: false,
    quiet: false,
    saveAllAttempts: false
  },
  e2e: {
    setupNodeEvents(on, config) 
    {
      require('cypress-mochawesome-reporter/plugin')(on);
    },
    defaultCommandTimeout: 20000,
    pageLoadTimeout: 30000,
    viewportWidth: 1920,
    viewportHeight: 1080
  }
});