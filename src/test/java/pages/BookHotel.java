package pages;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import base.BaseClass;
import utils.CommonMethod;
import utils.ExcelUtil;
import utils.PropReader;

public class BookHotel extends BaseClass {
	
	Properties prop = PropReader.property(); // Properties object for reading configuration properties
	CommonMethod cm = new CommonMethod(); // CommonMethod object for reusable methods
	
	public BookHotel(WebDriver driver) {
		// Initialize elements using PageFactory
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	// Data-Driven where data is read using PropReader
	final String baseUrl = PropReader.prop.getProperty("baseUrl");
	
	// Visit the MakeMyTrip Page
	public void visitMakeMyTrip() throws InterruptedException {
		driver.get(baseUrl);
		//Thread.sleep(8000);
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
	
	public void clickHotelBtn() {
		WebElement clickHotel = driver.findElement(By.xpath("//*[@id=\"SW\"]/div[1]/div[2]/div/div/nav/ul/li[2]/div/a"));
		clickHotel.click();
	}
	
	public void roomsNGuests() {
		WebElement clickRoomsNGuests = driver.findElement(By.className("guests"));
		clickRoomsNGuests.click();
	}
	
	public void adultsNo() {
		WebElement noOfAdults = driver.findElement(By.xpath("//div[@class='rmsGst']/div[1]/div[2]/div[2]"));
		noOfAdults.click();
	}
	
	public void listAdult() throws Exception {
		// Find all the elements with the given XPath and store them in a list
	    List<WebElement> list = driver.findElements(By.xpath("//ul[@class='gstSlct__list']/li"));
	    
	    // Create a new ArrayList to store the text values of the elements
	    List<String> number = new ArrayList<String>();

	    // Iterate over each element in the list
	    for (int i = 0; i < list.size(); i++) {
	        WebElement ele = list.get(i); // Get the current element
	        number.add(ele.getText()); // Add the text value of the element to the 'number' list
	    }
	    System.out.println("--- Total List of Adults Who Can Get Accommodated ---");
	    System.out.println(number); // Print the list of adults

	    ExcelUtil.selectSheetName("BookHotel"); // Select the sheet in the Excel file
	    ExcelUtil.createRow(0);
	    ExcelUtil.setAdultCount(number); // Set the adult count in the Excel sheet
	    ExcelUtil.WriteExcel(); // Write to the Excel sheet
	}

	//Navigate back to the baseUrl or homepage of the MakeMyTrip website
	public void navigateBack() throws InterruptedException {
		driver.navigate().back();
//		driver.navigate().to("https://www.makemytrip.com/");
//		cm.handleModal();
		cm.pauseForSecs(2);
	}
}
