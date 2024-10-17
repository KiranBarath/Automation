package com.herokuapp.theinternet;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class NegativeTest {
	
	@Parameters({"username", "password", "expectedMessage"})
	@Test(priority = 1)
	public void negativeLoginTest(String username, String password, String expectedErrorMessage) {
		System.out.println("Starting Negative Login Test with username " + username + " password " + password);
//		Create Driver
		WebDriver driver = new ChromeDriver();
		sleep(1);

//		Open test page
		String url = "https://the-internet.herokuapp.com/login";
		driver.get(url);
		
		driver.manage().window().maximize();
		sleep(1);
		
//		Enter incorrect username
		WebElement usernameElement = driver.findElement(By.id("username"));
		usernameElement.sendKeys(username);
		sleep(1);
		
//		Enter password
		WebElement passwordElement = driver.findElement(By.id("password"));
		passwordElement.sendKeys(password);
		sleep(1);
		
//		Click login button
		WebElement loginButton = driver.findElement(By.tagName("button"));
		loginButton.click();
		
//		Verify error message
		WebElement errorMessage = driver.findElement(By.xpath("//div[@id='flash']"));
		String actualMessage = errorMessage.getText();
		Assert.assertTrue(actualMessage.contains(expectedErrorMessage), "Actual Message does not contains expected Message");
		
		
//		Closing browser
		driver.close();
		
		System.out.println("Test completed");
	}
	
	/*
	 * @Test(priority = 1) public void incorrectpassword() {
	 * System.out.println("Incorrect Password Test Started");
	 * 
	 * // Create Driver WebDriver driver = new ChromeDriver(); sleep(1);
	 * 
	 * // Open test page String url = "https://the-internet.herokuapp.com/login";
	 * driver.get(url);
	 * 
	 * driver.manage().window().maximize(); sleep(1);
	 * 
	 * // Enter correct username WebElement username =
	 * driver.findElement(By.id("username")); username.sendKeys("tomsmith");
	 * sleep(1);
	 * 
	 * // Enter incorrect password WebElement password =
	 * driver.findElement(By.id("password")); password.sendKeys("SuperSecret");
	 * sleep(1);
	 * 
	 * // Click login button WebElement loginButton =
	 * driver.findElement(By.tagName("button")); loginButton.click();
	 * 
	 * // Verify error message WebElement errorMessage =
	 * driver.findElement(By.xpath("//div[@id='flash']")); String expectedMessage =
	 * "Your password is invalid!"; String actualMessage = errorMessage.getText();
	 * Assert.assertTrue(actualMessage.contains(expectedMessage),
	 * "Actual Message does not contains expected Message");
	 * 
	 * 
	 * // Closing browser driver.close();
	 * 
	 * System.out.println("Test completed"); }
	 */
	
	private void sleep(int seconds) {
		try {
			Thread.sleep(seconds * 1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}	
}

