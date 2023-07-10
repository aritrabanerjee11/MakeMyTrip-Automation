package pages;

import java.util.Properties;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import base.BaseClass;
import utils.CommonMethod;
import utils.ExcelUtil;
import utils.PropReader;

public class GiftCard extends BaseClass {
	
	Properties prop = PropReader.property(); // Properties object for reading configuration properties
	CommonMethod cm = new CommonMethod(); // CommonMethod object for reusable methods

	public GiftCard(WebDriver driver) {
		// Initialize elements using PageFactory
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	// Data-Driven where data is read using PropReader
	final String giftUrl = PropReader.prop.getProperty("giftUrl");
	final String name = PropReader.prop.getProperty("name");
	final String number = PropReader.prop.getProperty("number");
	final String emailId = PropReader.prop.getProperty("emailId");
	
	@FindBy(xpath = "//*[@id=\"root\"]/div/div[2]/div/div[1]/div/div[2]/div[8]/ul/li/div/img")
	private WebElement card;
	
	@FindBy(name = "senderName")
	private WebElement senderName;
	
	@FindBy(name = "senderMobileNo")
	private WebElement senderNumber;
	
	@FindBy(name = "senderEmailId")
	private WebElement senderEmailId;	
	
	@FindBy(className = "prime__btn__text")
	private WebElement button;
	
	@FindBy(className = "font11")
	private WebElement errormsg;
	
	// Visit the Gift-Card URL
	public void visitMakeMyTrip() throws InterruptedException {
		driver.get(giftUrl);
//		Thread.sleep(8000);
		cm.pauseForSecs(8);
	}
	
	public void selectingCard() throws InterruptedException {
		JavascriptExecutor js = (JavascriptExecutor) driver; // Scroll the page to bring the 'card' element into view
		js.executeScript("arguments[0].scrollIntoView()", card);
//		Thread.sleep(2000);
		cm.pauseForSecs(2);
		js.executeScript("arguments[0].click();", card); // Click on the 'card' element using JavaScriptExecutor
	}
	
	public void sendingKeys() {
		senderName.sendKeys(name);
		senderNumber.sendKeys(number);
		senderEmailId.sendKeys(emailId);
	}
	
	public void submitting() {
		button.click();
	}
	
	public void showError() throws Exception {
		String errMsg = errormsg.getText(); // Get the error message from the WebElement
		System.out.println("--- ERROR MESSAGE ---");
		System.out.println(errMsg);
		
		String errName = "Error Message";
		
		ExcelUtil.selectSheetName("GiftCard"); // Select the sheet name in the Excel file
	    ExcelUtil.createRow(0);
	    ExcelUtil.setHeadingForError(errName); // Set the heading for the error message column in the Excel sheet
	    ExcelUtil.createRow(1);
	    ExcelUtil.setErrorMsg(errMsg); // Set the error message value in the Excel sheet
	    ExcelUtil.WriteExcel(); // Write to the Excel sheet
	}
}