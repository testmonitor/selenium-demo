package com.testmonitor.beaker;

import com.testmonitor.listener.interfaces.HasWebdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class LogoutTests implements HasWebdriver {
	protected WebDriver driver;

	@BeforeMethod(alwaysRun = true)
	private void setUp() {
		System.setProperty("webdriver.chrome.driver", System.getenv("CHROMEDRIVER_PATH"));
		this.driver = new ChromeDriver();
		this.driver.manage().window().maximize();
	}

	@Test
	public void logoutTest() {
		
		String websiteUrl = "https://beaker.testmonitor.com/login";
		this.driver.get(websiteUrl);

		WebElement emailField = this.driver.findElement(By.id("email"));
		emailField.sendKeys("beaker@testmonitor.com");

		WebElement passwordField = this.driver.findElement(By.id("password"));
		passwordField.sendKeys("7SkmdtRffYweHM68");

		WebElement logInButton = this.driver.findElement(By.tagName("button"));
		logInButton.click();
		
		WebDriverWait wait = new WebDriverWait(driver, 2);
		WebElement profileButton = wait.until(ExpectedConditions.elementToBeClickable(By.tagName("button")));
		profileButton.click();
		
		WebElement logoutButton = this.driver.findElement(By.xpath("//button[.=' Log Out ']"));
		logoutButton.click();
		
		WebElement testMonitorLink = wait.until(ExpectedConditions.elementToBeClickable(By.linkText("TestMonitor")));
		Assert.assertTrue(testMonitorLink.isDisplayed(), "Link to TestMonitor page is not visible.");
		
		String expectedUrl = "https://beaker.testmonitor.com/";
		String actualUrl = this.driver.getCurrentUrl();
		Assert.assertEquals(actualUrl, expectedUrl, "Actual page URL is not the same as expected.");
	}
	
	@AfterMethod(alwaysRun = true)
	private void tearDown() {
		this.driver.quit();
	}

	public WebDriver getDriver() {
		return this.driver;
	}

}
