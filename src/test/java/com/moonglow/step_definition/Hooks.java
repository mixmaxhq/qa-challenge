package com.moonglow.step_definition;

import com.moonglow.utilities.ConfigurationReader;
import com.moonglow.utilities.Driver;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;


public class Hooks {

    @Before
    public void setup(){
        Driver.getDriver().get(ConfigurationReader.getProperty("url"));
    }

    @After
    public void teardownScenario(Scenario scenario) {
        if (scenario.isFailed()) {
            byte[] screenshots = ((TakesScreenshot) Driver.getDriver()).getScreenshotAs(OutputType.BYTES);
            scenario.attach(screenshots, "image/png", scenario.getName());
        }

        Driver.closeDriver();
    }



}
