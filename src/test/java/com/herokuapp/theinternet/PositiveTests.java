package com.herokuapp.theinternet;

import org.openqa.selenium.By;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * @author 892804
 *
 */
public class PositiveTests {

	@Test
	public void loginTest() {
		System.out.println("Test started for positive test");
// 		Create driver
		WebDriver driver = new ChromeDriver();
		sleep(1);
		// Just checking

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
	//	Assert.assertEquals(actualText, expectedText, "Actual message is not same as expected");
		Assert.assertTrue(actualText.contains(expectedText), "Actual Text does not contains expected Text");

//		Closing browser
		driver.close();

		System.out.println("Test Finished");

	}

	private void sleep(int seconds) {
		try {
			Thread.sleep(seconds * 1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
