package com.testmonitor.beaker.tests;

import com.testmonitor.beaker.pages.*;
import com.testmonitor.testng.interfaces.HasWebdriver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.time.Duration;

public class BaseTest implements HasWebdriver{
    protected WebDriver driver;
    protected LandingPage landingPage;
    protected LoginPage loginPage;
    protected TaskPage taskPage;
    protected PasswordResetPage passwordResetPage;

    @BeforeMethod
    public void setUp() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-blink-features=AutomationControlled");
        options.addArguments("--headless=new");
        options.addArguments("--window-size=1920,1080");

        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));

        // Initialize page objects
        landingPage = new LandingPage(driver);
        loginPage = new LoginPage(driver);
        taskPage = new TaskPage(driver);
        passwordResetPage = new PasswordResetPage(driver);
    }

    /**
     * By implementing the getDriver method, the TestMonitor TestNG listener can access
     * the WebDriver instance and add screenshots to the report.
     */
    @Override
    public WebDriver getDriver() {
        return driver;
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
