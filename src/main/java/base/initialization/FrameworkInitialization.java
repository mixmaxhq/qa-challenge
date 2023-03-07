package base.initialization;

import base.TestBase;
import base.context.LocalDriverContext;
import base.enums.BrowserType;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Properties;

public class FrameworkInitialization extends TestBase {
    private static final Logger frameworkInitializeLogger = LogManager.getLogger(FrameworkInitialization.class);

    public static Properties readBrowserConfig(String configFileName) throws IOException {
        Properties properties = new Properties();
        InputStream inputStream = new FileInputStream(System.getProperty("user.dir") + "/src/main/resources/" + configFileName + ".properties");
        properties.load(inputStream);
        frameworkInitializeLogger.info(configFileName + ".properties reading is successfully..");
        return properties;
    }

    public static WebDriver InitializeBrowser(BrowserType browserType) throws Throwable {

        RemoteWebDriver driver = null;
        Properties configProperties = readBrowserConfig("config");
        Properties browserConfigProperties = readBrowserConfig(browserType.toString().toLowerCase());

        String os = System.getProperty("os.name").toLowerCase();
        frameworkInitializeLogger.info("Operating System: " + os);

        switch (browserType) {
            case FIREFOX: {
                FirefoxOptions firefoxOptions = new FirefoxOptions();
                FirefoxProfile firefoxProfile = new FirefoxProfile();
                if (browserConfigProperties.isEmpty()) {
                    frameworkInitializeLogger.info(browserType.toString().toLowerCase() + " properties does not contains capabilities..");
                } else {
                    for (String key : browserConfigProperties.stringPropertyNames()) {
                        String value = browserConfigProperties.getProperty(key);
                        firefoxProfile.setPreference(key, value);
                    }
                    firefoxProfile.setAssumeUntrustedCertificateIssuer(false);
                    firefoxOptions.setProfile(firefoxProfile);
                    firefoxOptions.setAcceptInsecureCerts(true);
                }
                if (configProperties.getProperty("remote") == null || configProperties.getProperty("remote").equalsIgnoreCase("false")) {
                    driver = new FirefoxDriver(firefoxOptions);
                    frameworkInitializeLogger.info("Firefox driver successfully initialized in LOCAL...");
                } else {
                    driver = new RemoteWebDriver(new URL(configProperties.getProperty("gridUrl")), firefoxOptions);
                    frameworkInitializeLogger.info("Firefox driver successfully initialized in REMOTE...");
                }
                break;
            }
            default: {
                ChromeOptions chromeOptions = new ChromeOptions();
                if (browserConfigProperties.isEmpty()) {
                    frameworkInitializeLogger.info(browserType.toString().toLowerCase() + " properties does not contains capabilities..");
                } else {
                    for (String key : browserConfigProperties.stringPropertyNames()) {
                        String value = browserConfigProperties.getProperty(key);
                        if (value == null || value.isEmpty()) {
                            chromeOptions.addArguments(key);
                        } else {
                            chromeOptions.setCapability(key, value);
                        }
                    }
                }
                chromeOptions.setCapability(CapabilityType.ACCEPT_INSECURE_CERTS, true);
                if (configProperties.getProperty("remote") == null || configProperties.getProperty("remote").equalsIgnoreCase("false")) {
                    driver = new ChromeDriver(chromeOptions);
                    frameworkInitializeLogger.info("Chrome driver successfully initialized in LOCAL...");
                } else {
                    driver = new RemoteWebDriver(new URL(configProperties.getProperty("gridUrl")), chromeOptions);
                    frameworkInitializeLogger.info("Chrome driver successfully initialized in REMOTE...");
                }
                break;
            }
        }
        LocalDriverContext.setRemoteWebDriverThreadLocal(driver);
        return driver;
    }
}
