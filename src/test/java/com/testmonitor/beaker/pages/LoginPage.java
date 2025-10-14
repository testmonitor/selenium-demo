package com.testmonitor.beaker.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage extends BasePage {
    // Locators
    private final By signInHeading = By.xpath("//h2");
    private final By emailField = By.id("email");
    private final By passwordField = By.id("password");
    private final By submitButton = By.xpath("//button[@type='submit']");
    private final By loginError = By.id("loginError");
    private final By forgotPasswordLink = By.xpath("//a[contains(text(), 'Forgot your password?')]");

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public String getSignInHeadingText() {
        return getText(signInHeading);
    }

    public boolean isEmailFieldVisible() {
        return isElementVisible(emailField);
    }

    public boolean isPasswordFieldVisible() {
        return isElementVisible(passwordField);
    }

    public TaskPage loginWithCredentials(String email, String password) {
        typeText(emailField, email);
        typeText(passwordField, password);
        clickElement(submitButton);
        return new TaskPage(driver);
    }

    public void attemptLogin(String email, String password) {
        typeText(emailField, email);
        typeText(passwordField, password);
        clickElement(submitButton);
    }

    public boolean isLoginErrorVisible() {
        return isElementVisible(loginError);
    }

    public PasswordResetPage clickForgotPassword() {
        clickElement(forgotPasswordLink);
        return new PasswordResetPage(driver);
    }
}
