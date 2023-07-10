package tests;

import java.lang.reflect.Method;

import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.aventstack.extentreports.MediaEntityBuilder;

import base.BaseTest;
import pages.BookHotel;
import utils.ScreenshotUtil;

public class BookHotelTests extends BaseTest {

	private BookHotel bookHotel;

	@BeforeMethod
	void setPage(Method method) {
		bookHotel = new BookHotel(driver);
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
			bookHotel.visitMakeMyTrip();
//			bookHotel.handleModal();
			test.pass("Make My Trip Website Visited Sucessfully");
		} catch (Exception e) {
			test.fail(e);
		}
		// Add assertions or further test steps as needed
	}

	@Test(priority = 2, dependsOnMethods = "testVisitMakeMyTrip")
	public void testHandleModal() throws InterruptedException {
//		MakeMyTripPage.visitMakeMyTrip();
		try {
			bookHotel.handleModal();
			test.pass("Modal Handled Sucessfully");
		} catch (Exception e) {
			test.fail(e);
		}
		// Add assertions or further test steps as needed
	}

	@Test(priority = 3, dependsOnMethods = "testHandleModal")
	public void testClickHotelBtn() {
		try {
			bookHotel.clickHotelBtn();
			test.pass("Hotel Button Visited Sucessfully");
		} catch (Exception e) {
			test.fail(e);
		}
	}

	@Test(priority = 4, dependsOnMethods = "testClickHotelBtn")
	public void testRoomsNGuest() {
		try {
			bookHotel.roomsNGuests();
			test.pass("Rooms and Guests selected Succesfully");
		} catch (Exception e) {
			test.fail(e);
		}
	}

	@Test(priority = 5, dependsOnMethods = "testRoomsNGuest")
	public void testAdultsNo() {
		try {
			bookHotel.adultsNo();
			test.pass("Number of adults tab selected successfully");
		} catch (Exception e) {
			test.fail(e);
		}
	}
//	@Test(priority = 6,dependsOnMethods = "testAdultsNo")
//	public void testGetAdultNo() {
//		bookHotel.getAdultNo();
//	}

	@Test(priority = 6, dependsOnMethods = "testAdultsNo")
	public void testListAdult() throws Exception {
		try {
			bookHotel.listAdult();
			test.pass("List of Adults Shown Successfully");
		} catch (Exception e) {
			test.fail(e);
		}
	}

	@Test(priority = 7, dependsOnMethods = "testListAdult")
	public void testNavigateBack() throws InterruptedException {
		try {
			bookHotel.navigateBack();
			test.pass("Successful navigation to home page");
		} catch (Exception e) {
			test.fail(e);
		}
	}

}
