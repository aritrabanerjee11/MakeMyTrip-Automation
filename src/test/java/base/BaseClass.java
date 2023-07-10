package base;

import java.time.Duration;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.ITestContext;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;

import utils.ExtentReportManager;

public class BaseClass {
	protected WebDriver driver; // WebDriver object to control the browser
	protected ExtentReports extent; // ExtentReports object for generating reports
	protected String browserName; // Stores the name of the browser being used
	protected ExtentTest test; // ExtentTest object for test-specific reporting

    // Initializes the WebDriver based on the provided browser parameter
	@Parameters("browser")
	@BeforeClass
	public void initDriver(@Optional("chrome") String browser) {
		browserName = browser;
		if (browser.equalsIgnoreCase("chrome")) {
			System.setProperty("webdriver.chrome.driver", "./Drivers/chromedriver.exe");
			driver = new ChromeDriver();
		} else if (browser.equalsIgnoreCase("edge")) {
			System.setProperty("webdriver.edge.driver", "./Drivers/msedgedriver.exe");
			driver = new EdgeDriver();
		} else {
			throw new IllegalArgumentException("Invalid browser name provided!");
		}
		
		// Maximize the browser window, delete cookies and set timeouts
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
	}
	
    // Initializes the ExtentReports object for generating reports
	@BeforeClass
	public void initExtentReport(ITestContext ctx) {
		ExtentReportManager.getInstance(ctx);
		
	}
	
    // Closes the browser after the test class execution is completed
	@AfterClass
	public void tearDown() {
		driver.quit();
	}

    // Flushes out the ExtentReports object to write the report to the output file
	@AfterSuite
	public void closeExtentReport() {
		ExtentReportManager.extent.flush();
	}

}
