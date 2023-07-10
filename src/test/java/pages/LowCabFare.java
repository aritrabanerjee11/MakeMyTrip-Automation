package pages;

import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import base.BaseClass;
import utils.CommonMethod;
import utils.ExcelUtil;
import utils.PropReader;

public class LowCabFare extends BaseClass {
	
	Properties prop = PropReader.property(); // Properties object for reading configuration properties
	CommonMethod cm = new CommonMethod(); // CommonMethod object for reusable methods

	public LowCabFare(WebDriver driver) {
		// Initialize elements using PageFactory
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	// Data-Driven where data is read using PropReader
	final String baseUrl = PropReader.prop.getProperty("baseUrl");
	final String destination = PropReader.prop.getProperty("destination");
	
	@FindBy(id = "webengage-notification-container")
	private WebElement closeModalBtn;
	
	@FindBy(xpath = "//a[contains(@href,'cabs')]")
	private WebElement cabsLink;
	
	@FindBy(className = "lineHeight36")
	private WebElement oneWayOutstationLink;
	
	@FindBy(xpath = "//span[text()='Delhi']")
	private WebElement sourceLink;
	
	@FindBy(xpath = "//*[@id=\"root\"]/div/div[2]/div/div/div[2]/div[1]/div[1]/div[1]/div/div/div/input")
	private WebElement sourceInput;
	
	@FindBy(className = "react-autosuggest__input--open")
	private WebElement destinationInput;
	
	@FindBy(id = "react-autowhatever-1-section-0-item-0")
	private WebElement destinationLink;
	
	@FindBy(className = "DayPicker-NavButton--next")
	private WebElement nextMonthLink;
	
	@FindBy(xpath = "//div[@aria-label='Wed Aug 23 2023']")
	private WebElement dateSelectorLink;
	
	@FindBy(className = "timePicker")
	private WebElement timePickerLink;
	
	@FindBy(xpath = "//span[text()='06  Hr']")
	private WebElement hourPickerLink;
	
	@FindBy(xpath = "//span[text()='30  min']")
	private WebElement minPickerLink;
	
	@FindBy(className = "applyBtnText")
	private WebElement applyLink;
	
	@FindBy(xpath = "//a[@data-cy='OutstationOneWayWidget_64']")
	private WebElement searchLink;
	
	@FindBy(className = "dodgerBlueColor")
	private WebElement sortLink;
	
	@FindBy(xpath = "//p[text()='Price']")
	private WebElement lowestFare;
	
	@FindBy(xpath = "//*[@id=\"root\"]/div/div[2]/div[2]/div[2]/div[1]/div/div[2]/div[3]/span/label")
//	@FindBy(xpath = "//*[@id=\"root\"]/div/div[2]/div[2]/div[2]/div[1]/div/div[2]/div[2]/span/label")
	private WebElement selectSuv;
	
	@FindBy(xpath = "/html/body/div/div/div[2]/div[2]/div[2]/div[2]/div[3]/div/div/div[1]/div/div[2]/div[1]/div[1]/span[1]")
	private WebElement carName;
	
	@FindBy(xpath = "/html/body/div/div/div[2]/div[2]/div[2]/div[2]/div[3]/div/div/div[1]/div/div[3]/div/div[2]/div/p[1]")
	private WebElement carPrice;
	
	@FindBy(xpath = "//*[@id=\"List\"]/div[1]/div")
	private WebElement list;
	
	// Visit the MakeMyTrip Website
	public void visitMakeMyTrip() throws InterruptedException {
		driver.get(baseUrl);
//		Thread.sleep(8000);
		cm.pauseForSecs(8);
//		cm.handleModal();
	}
	
	public void handleModal() throws InterruptedException {
		WebElement closeModal = driver.findElement(By.id("webengage-notification-container")); // Find the close modal button
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOf(closeModal)); // Wait for the close modal button to be visible
		Thread.sleep(1000);
		Actions actions = new Actions(driver);
		actions.moveByOffset(0, 0).click().build().perform(); // Perform a mouse click action at the current position (0, 0)
	}
	
	public void clickCabsLink() { 
		cabsLink.click();
	}
	
	public void clickOneWayOutstationLink() {
		oneWayOutstationLink.click();
	}
	
	public void clickSourceLink() {
		sourceLink.click();
	}
	
	public void clickDestinationLink() {
		destinationLink.click();
	}
	
	public void clickNextMonthLink() {
		nextMonthLink.click();
	}
	
	public void clickDateSelectorLink() {
		dateSelectorLink.click();
	}
	
	public void clickTimePickerLink() {
		timePickerLink.click();
		hourPickerLink.click();
		minPickerLink.click();
	}
	
//	public void clickHourPickerLink() {
//		hourPickerLink.click();
//	}
//	
//	public void clickMinPickerLink() {
//		minPickerLink.click();
//	}
	
	public void clickApplyLink() {
		applyLink.click();
	}
	
	public void clickSearchLink() {
		searchLink.click();
	}
	
	public void clickSortLink() {
		sortLink.click();
	}
	
	public void clickLowestFare() {
		lowestFare.click();
	}
	
	public void enterDestination() throws InterruptedException {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		wait.until(ExpectedConditions.elementToBeClickable(destinationLink));
		destinationInput.sendKeys(destination);
//		Thread.sleep(10000);
		cm.pauseForSecs(5);
		destinationInput.sendKeys(Keys.ENTER);
	}
	
	/** Clicking on the SUV option with JavascriptExecutor
	* This is done to overcome any potential issues with the click() method
	*/
	public void selectSuv() throws InterruptedException {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.elementToBeClickable(selectSuv));
		JavascriptExecutor js = (JavascriptExecutor) driver;
		
		js.executeScript("arguments[0].click();",selectSuv);
//		selectSuv.click();
		
	}
	
//	public void selectSuv() throws InterruptedException {
//		WebElement suv = driver.findElement(By.xpath("//*[@id=\"root\"]/div/div[2]/div[2]/div[2]/div[1]/div/div[2]/div[3]//span"));
//		Thread.sleep(1000);
//		boolean select = suv.isSelected();
//		if(select == false) {
//			suv.click();
//		}
//	}
	
	//Data-Driven where the result is being written to an Excel Sheet
	public void getPrice() throws Exception {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView()", list); // Scroll to the list element
        
        String hName = "Car Name";
        String hPrice = "Price";

        System.out.println("--- Lowest Car Price And Name ---");
        String cabName = carName.getText(); // Get the car name
        System.out.println(cabName);
        String price = carPrice.getText(); // Get the car price
        System.out.println(price);

        ExcelUtil.selectSheetName("LowCabFare");
        ExcelUtil.createRow(0);
        ExcelUtil.setHeadingForCabs(hName, hPrice); // Set the heading for the car name and price in the Excel sheet
        ExcelUtil.createRow(1);
        ExcelUtil.setValue(cabName, price); // Set the car name and price values in the Excel sheet
        ExcelUtil.WriteExcel(); // Write to the Excel sheet
    }

}

