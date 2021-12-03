package test.com.testmonitor.beaker;

import com.testmonitor.listener.interfaces.HasWebdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class LoginTests implements HasWebdriver {
	protected WebDriver driver;

	@BeforeMethod(alwaysRun = true)
	private void setUp() {
		System.setProperty("webdriver.chrome.driver", System.getenv("CHROMEDRIVER_PATH"));
		this.driver = new ChromeDriver();
		this.driver.manage().window().maximize();
		this.driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
	}

	@Test(priority = 1)
	public void positiveLoginTest() {

		String websiteUrl = "https://beaker.testmonitor.com/login";
		this.driver.get(websiteUrl);

		WebElement emailField = this.driver.findElement(By.id("email"));
		emailField.sendKeys("beaker@testmonitor.com");

		WebElement passwordField = this.driver.findElement(By.id("password"));
		passwordField.sendKeys("7SkmdtRffYweHM68");

		WebElement logInButton = this.driver.findElement(By.tagName("button"));
		logInButton.click();

		WebElement navigationMenu = this.driver.findElement(By.tagName("nav"));
		Assert.assertTrue(navigationMenu.isDisplayed(), "Navigation menu is not visible.");
		
		String expectedUrl = "https://beaker.testmonitor.com/dashboard";
		String actualUrl = this.driver.getCurrentUrl();
		Assert.assertEquals(actualUrl, expectedUrl, "Actual page URL is not the same as expected.");

	}

	@Test(priority = 2)
	public void incorrectEmailTest() {

		String websiteUrl = "https://beaker.testmonitor.com/login";
		this.driver.get(websiteUrl);

		WebElement emailField = this.driver.findElement(By.id("email"));
		emailField.sendKeys("breaker@testmonitor.com");

		WebElement passwordField = this.driver.findElement(By.id("password"));
		passwordField.sendKeys("7SkmdtRffYweHM68");

		WebElement logInButton = this.driver.findElement(By.tagName("button"));
		logInButton.click();

		WebElement successMessage = this.driver.findElement(By.tagName("li"));
		String expectedMessage = "These credentials do not match our records.";
		String actualMessage = successMessage.getText();
		Assert.assertTrue(actualMessage.contains(expectedMessage), "Actual message does not contain expected message."
				+ "\nActual Message: " + actualMessage + "\nExpected Message: " + expectedMessage);
	}

	@Test(priority = 3)
	public void incorrectPasswordTest() {

		String websiteUrl = "https://beaker.testmonitor.com/login";
		this.driver.get(websiteUrl);

		WebElement emailField = this.driver.findElement(By.id("email"));
		emailField.sendKeys("beaker@testmonitor.com");

		WebElement passwordField = this.driver.findElement(By.id("password"));
		passwordField.sendKeys("8SkmdtRffYweHM68");

		WebElement logInButton = this.driver.findElement(By.tagName("button"));
		logInButton.click();
		
		WebElement successMessage = this.driver.findElement(By.tagName("li"));
		String expectedMessage = "These credentials do not match our records.";
		String actualMessage = successMessage.getText();
		Assert.assertTrue(actualMessage.contains(expectedMessage), "Actual message does not contain expected message."
				+ "\nActual Message: " + actualMessage + "\nExpected Message: " + expectedMessage);
	}

	@AfterMethod(alwaysRun = true)
	protected void tearDown() {
		this.driver.quit();
	}

	public WebDriver getDriver() {
		return this.driver;
	}
}
