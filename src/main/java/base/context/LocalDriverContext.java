package base.context;

import org.openqa.selenium.WebDriver;

public class LocalDriverContext {
    private static final ThreadLocal<WebDriver> driverThread = new ThreadLocal<>();

    public static WebDriver getRemoteWebDriver() {
        return driverThread.get();
    }

    public static void setRemoteWebDriverThreadLocal(WebDriver driver) {
        driverThread.set(driver);
    }
}
