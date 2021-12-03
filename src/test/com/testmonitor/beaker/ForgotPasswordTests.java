package test.com.testmonitor.beaker;

import com.testmonitor.listener.interfaces.HasWebdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class ForgotPasswordTests implements HasWebdriver {
	protected WebDriver driver;

	@BeforeMethod(alwaysRun = true)
	private void setUp() {
		System.setProperty("webdriver.chrome.driver", System.getenv("CHROMEDRIVER_PATH"));
		this.driver = new ChromeDriver();
		this.driver.manage().window().maximize();
	}
	
	@Test
	public void forgotPasswordTest() {
		
		String websiteUrl = "https://beaker.testmonitor.com/login";
		this.driver.get(websiteUrl);

		WebElement forgotPasswordLink = this.driver.findElement(By.linkText("Forgot your password?"));
		forgotPasswordLink.click();

		String expectedUrl = "https://beaker.testmonitor.com/forgot-password";
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
