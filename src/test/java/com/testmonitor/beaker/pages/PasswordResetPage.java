package com.testmonitor.beaker.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class PasswordResetPage extends BasePage {
    // Locators
    private final By resetHeading = By.xpath("//div[@id='resetPage']//h2");
    private final By resetEmailField = By.id("resetEmail");
    private final By sendResetLinkButton = By.xpath("//button[contains(text(), 'Send Reset Link')]");
    private final By resetError = By.id("resetError");

    public PasswordResetPage(WebDriver driver) {
        super(driver);
    }

    public String getResetHeadingText() {
        return getText(resetHeading);
    }

    public void fillResetEmail(String email) {
        typeText(resetEmailField, email);
    }

    public void clickSendResetLink() {
        clickElement(sendResetLinkButton);
    }

    public boolean isResetErrorVisible() {
        return isElementVisible(resetError);
    }

    public boolean isResetErrorNotVisible() {
        return isElementNotVisible(resetError);
    }

    public String getResetErrorText() {
        return getText(resetError);
    }
}
