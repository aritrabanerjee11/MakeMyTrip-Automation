package tests;

import java.lang.reflect.Method;

import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.aventstack.extentreports.MediaEntityBuilder;

import base.BaseTest;
import pages.GiftCard;
import utils.ScreenshotUtil;

public class GiftCardTests extends BaseTest{
	
	private GiftCard giftCardTests;
	
	@BeforeMethod
	void setPage(Method method) {
		giftCardTests = new GiftCard(driver);
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
			giftCardTests.visitMakeMyTrip();
			test.pass("Gift Card Website Visited Sucessfully");
		} catch (Exception e) {
			test.fail(e);
		}
	}
	
	@Test(priority = 2, dependsOnMethods = "testVisitMakeMyTrip")
	public void testSelectingCard() throws InterruptedException {
		try {
			giftCardTests.selectingCard();
			test.pass("Gift Card selected Sucessfully");
		} catch (Exception e) {
			test.fail(e);
		}
	}

	@Test(priority = 3, dependsOnMethods = "testSelectingCard")
	public void testSendingKeys() throws InterruptedException {
		try {
			giftCardTests.sendingKeys();
			test.pass("Sender value sent successfully");
		} catch (Exception e) {
			test.fail(e);
		}
	}
	
	@Test(priority = 4,dependsOnMethods = "testSendingKeys")
	public void testSubmitting() {
		try {
			giftCardTests.submitting();
			test.pass("Submit button clicked Sucessfully");
		} catch (Exception e) {
			test.fail(e);
		}
	}
	
	@Test(priority = 5, dependsOnMethods =  "testSubmitting")
	public void testShowError() throws Exception {
		try {
			giftCardTests.showError();
			test.pass("Error Message Captured Successfully");
		} catch (Exception e) {
			test.fail(e);
		}
	}
}
