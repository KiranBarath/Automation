
package com.herokuapp.theinternet;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

/**
 * @author 892804
 *
 */
public class LoginTests {

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

	@Test(priority = 1, groups = { "positiveTests", "smokeTests" })
	public void positiveLoginTest() {
		System.out.println("Test started");

// 		open test page
		String url = "https://the-internet.herokuapp.com/login";
		driver.get(url);
		sleep(1);

		driver.manage().window().maximize();
		sleep(1);

		System.out.println("Page is opened");

// 		Enter User Name
		WebElement username = driver.findElement(By.id("username"));
		username.sendKeys("tomsmith");
		sleep(2);

// 		Enter Password
		WebElement password = driver.findElement(By.id("password"));
		password.sendKeys("SuperSecretPassword!");
		sleep(2);

// 		Click Login Button
		WebElement loginbutton = driver.findElement(By.tagName("button"));
		loginbutton.click();
		sleep(3);

//		verify url
		String expectedUrl = "https://the-internet.herokuapp.com/secure";
		String actualUrl = driver.getCurrentUrl();
		Assert.assertEquals(actualUrl, expectedUrl, "Actual page url is not same as expected");

// 		Logout Button is visible
		WebElement logoutbutton = driver.findElement(By.xpath("//a[@class = 'button secondary radius']"));
		Assert.assertTrue(logoutbutton.isDisplayed(), "Logout button is not visible");

//		verify success message
		WebElement successMessage = driver.findElement(By.xpath("//div[@id='flash']"));
		String expectedText = "You logged into a secure area!";
		String actualText = successMessage.getText();
		// Assert.assertEquals(actualText, expectedText, "Actual message is not same as
		// expected");
		Assert.assertTrue(actualText.contains(expectedText), "Actual Text does not contains expected Text");

		System.out.println("Test Finished");

	}


	@Parameters({ "username", "password", "expectedMessage" })
	@Test(priority = 2, groups = { "negativeTests", "smokeTests" })
	public void negativeLoginTest(String username, String password, String expectedErrorMessage) {
		System.out.println("Starting Negative Login Test with username " + username + " password " + password);

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
		Assert.assertTrue(actualMessage.contains(expectedErrorMessage),
				"Actual Message does not contains expected Message");

		System.out.println("Test completed");
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
		//		Closing browser
		driver.close();
	}
}
