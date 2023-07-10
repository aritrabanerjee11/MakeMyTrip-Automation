package utils;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
public class CommonMethod {
	
	/**
     * Pauses the execution for the specified number of seconds.
     * @param seconds the number of seconds to pause
     */
	public WebDriver driver;

    public void pauseForSecs(long mul) {
        try {
            Thread.sleep(mul*1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    
    /**
     * Handles the modal that pops up while visit MakeMyTrip homepage
     */
	public void handleModal() throws InterruptedException {
		WebElement closeModal = driver.findElement(By.id("webengage-notification-container")); // Find the close modal button
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOf(closeModal)); // Wait for the close modal button to be visible
		Thread.sleep(1000);
		Actions actions = new Actions(driver);
		actions.moveByOffset(0, 0).click().build().perform(); // Perform a mouse click action at the current position (0, 0)
	}


}
