package utils;

import org.testng.ISuite;
import org.testng.ITestContext;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentReportManager {
	public static ExtentReports extent;

    // Method to get an instance of ExtentReports
	public static ExtentReports getInstance(ITestContext ctx) {
		if (extent == null) {
			ISuite suite = ctx.getSuite();
			String suiteName;
            // Get the suite name
			if (suite.getXmlSuite().getParentSuite() != null) {
				suiteName = suite.getXmlSuite().getParentSuite().getName();
			} else {
				suiteName = suite.getName();
			}

            // Set the report file name based on the suite name
			String reportName = "Reports/ExtentReport_" + suiteName + ".html";

            // Create an ExtentSparkReporter instance with the report file name
			ExtentSparkReporter htmlReporter = new ExtentSparkReporter(reportName);
			
            // Configure the HTML report
			htmlReporter.config().setDocumentTitle(suiteName + " Test Report");
			htmlReporter.config().setReportName(suiteName + " Execution Report");
			htmlReporter.config().setTheme(Theme.DARK);

            // Create an instance of ExtentReports
			extent = new ExtentReports();
			
            // Attach the ExtentSparkReporter to the ExtentReports instance
			extent.attachReporter(htmlReporter);
		}
		
        // Return the ExtentReports instance
		return extent;
	}

}