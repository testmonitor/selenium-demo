package com.testmonitor.beaker.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class LoginFlowTest extends BaseTest {

    @BeforeMethod
    public void navigateToLoginPage() {
        landingPage.navigateToLandingPage();
        loginPage = landingPage.clickGetStarted();
    }

    @Test(description = "Has correct login page heading - EXPECTED TO FAIL due to typo bug")
    public void testLoginPageHeading() {
        String headingText = loginPage.getSignInHeadingText();
        Assert.assertTrue(headingText.contains("Sign In to Beaker"),
            "Heading should contain 'Sign In to Beaker'");
    }

    @Test(description = "Displays email and password fields")
    public void testLoginFieldsVisible() {
        Assert.assertTrue(loginPage.isEmailFieldVisible(), "Email field should be visible");
        Assert.assertTrue(loginPage.isPasswordFieldVisible(), "Password field should be visible");
    }

    @Test(description = "Logs in with valid credentials")
    public void testSuccessfulLogin() {
        taskPage = loginPage.loginWithCredentials("test@example.com", "password123");
        String headingText = taskPage.getAppHeadingText();
        Assert.assertTrue(headingText.contains("Beaker"), "App heading should contain 'Beaker'");
        Assert.assertTrue(taskPage.isLogoutButtonVisible(), "Logout button should be visible");
    }

    @Test(description = "Rejects password shorter than 4 characters")
    public void testShortPasswordRejected() {
        loginPage.attemptLogin("test@example.com", "abc");
        Assert.assertTrue(loginPage.isLoginErrorVisible(), "Login error should be visible");
    }

    @Test(description = "Navigates to password reset page")
    public void testNavigateToPasswordReset() {
        passwordResetPage = loginPage.clickForgotPassword();
        String headingText = passwordResetPage.getResetHeadingText();
        Assert.assertTrue(headingText.contains("Reset Password"),
            "Heading should contain 'Reset Password'");
    }
}
