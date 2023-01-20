package pages;


import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.testng.ITestContext;
import org.testng.annotations.*;
import io.github.bonigarcia.wdm.WebDriverManager;



public class BasePage {

	public static WebDriver driver;
	public static ExtentTest logger;
	public static ExtentReports report;
	@BeforeSuite(alwaysRun=true)
	@Parameters({"loginPageURL"})
	public void setup1(ITestContext context,String url) {
		System.setProperty(ChromeDriverService.CHROME_DRIVER_SILENT_OUTPUT_PROPERTY, "true"); // This suppresses the Severe Timed out receiving messages
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get(url);
		context.setAttribute("WebDriver", driver);
	}
	@AfterSuite(alwaysRun=true)
	public void tearDown() {
		driver.quit();
	}
}
