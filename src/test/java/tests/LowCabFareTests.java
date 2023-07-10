package tests;

import java.lang.reflect.Method;

import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.aventstack.extentreports.MediaEntityBuilder;

import base.BaseTest;
import pages.LowCabFare;
import utils.ScreenshotUtil;

public class LowCabFareTests extends BaseTest {

	private LowCabFare lowCabFare;

	@BeforeMethod
	void setPage(Method method) {
		lowCabFare = new LowCabFare(driver);
		createTestWithMethodName(method, browserName);
	}
	
	@AfterMethod
	protected void takeFailedScreenshots(ITestResult result) {
	ScreenshotUtil sc = new ScreenshotUtil();
	String loc = sc.takeScreenshot(driver, result.getName());
	test.info("Screenshot", MediaEntityBuilder.createScreenCaptureFromPath(loc).build());
	}

	 

	@Test(priority = 1)
	public void testVisitMakeMyTrip() throws InterruptedException {
		try {
			lowCabFare.visitMakeMyTrip();
//			lowCabFare.handleModal();
			test.pass("Make My Trip Website Visited Sucessfully");
		} catch (Exception e) {
			test.fail(e);
		}
		// Add assertions or further test steps as needed
	}

	@Test(priority = 2, dependsOnMethods = "testVisitMakeMyTrip")
	public void testHandleModal() throws InterruptedException {
		try {
			lowCabFare.handleModal();
			test.pass("Modal Handled Sucessfully");
		} catch (Exception e) {
			test.fail(e);
		}
		// Add assertions or further test steps as needed
	}

	@Test(priority = 3, dependsOnMethods = "testHandleModal")
	public void testClickCabsLink() throws InterruptedException {
		try {
			lowCabFare.clickCabsLink();
			test.pass("Cabs Page Visited Sucessfully");
		} catch (Exception e) {
			test.fail(e);
		}
	}

	@Test(priority = 4,dependsOnMethods = "testClickCabsLink")
	public void testClickOneWayOutstationLink() {
		try {
			lowCabFare.clickOneWayOutstationLink();
			test.pass("One-Way Outstation Cabs Selected Sucessfully");
		} catch (Exception e) {
			test.fail(e);
		}
	}
	
	@Test(priority = 5,dependsOnMethods = "testClickOneWayOutstationLink")
	public void testClickSourceLink() throws InterruptedException {
		try {
			lowCabFare.clickSourceLink();
			test.pass("Source selected sucessfully");
		} catch (Exception e) {
			test.fail(e);
		}
	}
	
	@Test(priority = 6,dependsOnMethods = "testClickSourceLink")
	public void testEnterDestination() throws InterruptedException {
		try {
			lowCabFare.enterDestination();
			lowCabFare.clickDestinationLink();
			test.pass("Destination entered sucessfully");
		} catch (Exception e) {
			test.fail(e);
		}
	}
	
//	@Test(priority = 6,dependsOnMethods = "testEnterDestination")
//	public void testClickDestinationLink() {
//		try {
//			lowCabFare.clickDestinationLink();
//			test.pass("Destination selected sucessfully");
//		} catch (Exception e) {
//			test.fail(e);
//		}
//	}
	
//	@Test(priority = 7,dependsOnMethods = "testEnterDestination")
//	public void testClickNextMonthLink() {
//		try {
//			lowCabFare.clickNextMonthLink();
//			test.pass("Next Month selected sucessfully");
//		} catch (Exception e) {
//			test.fail(e);
//		}
//	}
	
	@Test(priority = 7,dependsOnMethods = "testEnterDestination")
	public void testClickDateSelectorLink() {
		try {
			lowCabFare.clickNextMonthLink();
			lowCabFare.clickDateSelectorLink();
			test.pass("Date Selector Clicked sucessfully");
		} catch (Exception e) {
			test.fail(e);
		}
	}
	
	@Test(priority = 8,dependsOnMethods = "testClickDateSelectorLink")
	public void testClickTimePickerLink() {
		try {
			lowCabFare.clickTimePickerLink();
//			lowCabFare.clickHourPickerLink();
//			lowCabFare.clickMinPickerLink();
//			lowCabFare.clickApplyLink();
			test.pass("Time For Travel Selected Sucessfully");
		} catch (Exception e) {
			test.fail(e);
		}
	}
	
//	@Test(priority = 9,dependsOnMethods = "testClickTimePickerLink")
//	public void testClickHourPickerLink() {
//		try {
//			lowCabFare.clickTimePickerLink();
//			test.pass("Hour Picker selected sucessfully");
//		} catch (Exception e) {
//			test.fail(e);
//		}
//	}
//	
//	@Test(priority = 10,dependsOnMethods = "testClickHourPickerLink")
//	public void testClickMinPickerLink() {
//		try {
//			lowCabFare.clickMinPickerLink();
//			test.pass("Minute Picker selected sucessfully");
//		} catch (Exception e) {
//			test.fail(e);
//		}
//	}
//	
	@Test(priority = 9,dependsOnMethods = "testClickTimePickerLink")
	public void testClickApplyLink() {
		try {
			lowCabFare.clickApplyLink();
			test.pass("Clicked on Apply Button");
		} catch (Exception e) {
			test.fail(e);
		}
	}
	
	@Test(priority = 10,dependsOnMethods = "testClickTimePickerLink")
	public void testClickSearchLink() {
		try {
			lowCabFare.clickSearchLink();
			test.pass("Clicked on Search Button");
		} catch (Exception e) {
			test.fail(e);
		}
	}
	
	@Test(priority = 11,dependsOnMethods = "testClickSearchLink")
	public void testClickSortLink() {
		try {
			lowCabFare.clickSortLink();
			test.pass("Clicked on Sort Button");
		} catch (Exception e) {
			test.fail(e);
		}
	}
	
	@Test(priority = 12,dependsOnMethods = "testClickSortLink")
	public void testSelectSuv() throws InterruptedException {
		try {
			lowCabFare.selectSuv();
			test.pass("SUV is selected");
		} catch (Exception e) {
			test.fail(e);
		}
	}
	
	@Test(priority = 13,dependsOnMethods = "testSelectSuv")
	public void testClickLowestFare() {
		try {
			lowCabFare.clickLowestFare();
			test.pass("Clicked on Lowest Price");
		} catch (Exception e) {
			test.fail(e);
		}
	}
	
	@Test(priority = 14, dependsOnMethods = "testClickLowestFare")
	public void testGetPrice() throws Exception {
		try {
			lowCabFare.getPrice();
			test.pass("Lowest Price found successfully");
		} catch (Exception e) {
			test.fail(e);
		}
	}

}
