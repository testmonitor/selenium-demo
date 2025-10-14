package com.testmonitor.beaker.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LandingPage extends BasePage {
    private static final String BASE_URL = "https://beaker.testmonitor.com/";

    // Locators
    private final By welcomeHeading = By.xpath("//h1[contains(text(), 'Welcome to Beaker')]");
    private final By getStartedButton = By.xpath("//button[contains(text(), 'Get Started')]");
    private final By testMonitorLink = By.xpath("//a[@href='https://www.testmonitor.com']");

    public LandingPage(WebDriver driver) {
        super(driver);
    }

    public void navigateToLandingPage() {
        driver.get(BASE_URL);
    }

    public String getPageTitle() {
        return driver.getTitle();
    }

    public boolean isWelcomeHeadingVisible() {
        return isElementVisible(welcomeHeading);
    }

    public String getWelcomeHeadingText() {
        return getText(welcomeHeading);
    }

    public LoginPage clickGetStarted() {
        clickElement(getStartedButton);
        return new LoginPage(driver);
    }

    public boolean isTestMonitorLinkVisible() {
        return isElementVisible(testMonitorLink);
    }

    public String getTestMonitorLinkText() {
        return getText(testMonitorLink);
    }
}
