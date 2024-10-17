package com.herokuapp.theinternet;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class ExceptionTests {

	private WebDriver driver;

	@Parameters("browser")
	@BeforeMethod(alwaysRun = true)
	private void setUp(String browser) {
// 		Create driver

		switch (browser) {
		case "chrome": {
			driver = new ChromeDriver();
			break;
		}

		case "firefox": {
			driver = new FirefoxDriver();
			break;
		}
		default:
			driver = new ChromeDriver();
			break;
		}

		sleep(1);
	}

	@Test
	public void noSuchElementExceptionTest() {

// 		open test page
		String url = "https://practicetestautomation.com/practice-test-exceptions/";
		driver.get(url);
		sleep(1);

		driver.manage().window().maximize();
		sleep(1);
		
//		Click Add button
		WebElement addButton = driver.findElement(By.id("add_btn"));
		addButton.click();
		
//		Implicit wait
//		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
//		Explicit wait
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='row2']/input")));
	
		
//		Verify Row 2 input field is displayed
		WebElement inputField = driver.findElement(By.xpath("//div[@id='row2']/input"));
		Assert.assertTrue(inputField.isDisplayed(), "Expected input field is not displayed");
	}
	
	@Test
	public void elementNotInteractableException() {
// 		open test page
		String url = "https://practicetestautomation.com/practice-test-exceptions/";
		driver.get(url);
		sleep(1);

		driver.manage().window().maximize();
		sleep(1);
		
//		Click Add button
		WebElement addButton = driver.findElement(By.id("add_btn"));
		addButton.click();
		
//		Implicit wait
//		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
//		Explicit wait
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='row2']/input")));
	
		
//		Verify Row 2 input field is displayed
		WebElement inputField = driver.findElement(By.xpath("//div[@id='row2']/input"));
		Assert.assertTrue(inputField.isDisplayed(), "Expected input field is not displayed");
		
//		Type text into the second input field
		inputField.sendKeys("Test");
		
		WebElement saveButton = driver.findElement(By.xpath("//div[@id='row2']/button[@name='Save']"));
		saveButton.click();
		
//		Verify text saved
		WebElement confirmationMessage = driver.findElement(By.id("confirmation"));
		String actualMessage = confirmationMessage.getText();
		Assert.assertEquals(actualMessage, "Row 2 was saved");
	}
	
	
	@Test
	public void invalidElementStateException () {
// 		open test page
		String url = "https://practicetestautomation.com/practice-test-exceptions/";
		driver.get(url);
		sleep(1);

		driver.manage().window().maximize();
		sleep(1);
		
//		Clear input field
		WebElement inputField1 = driver.findElement(By.xpath("//div[@id='row1']/input"));
		inputField1.clear();
		
//		Type text into the input field
		inputField1.sendKeys("Test");
		
//		Verify text changed
		String text = inputField1.getAttribute("value");
		Assert.assertEquals(text, "Test");
		
	}

	private void sleep(int seconds) {
		try {
			Thread.sleep(seconds * 1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@AfterMethod(alwaysRun = true)
	private void tearDown() {
		// Closing browser
		driver.close();
	}

}

