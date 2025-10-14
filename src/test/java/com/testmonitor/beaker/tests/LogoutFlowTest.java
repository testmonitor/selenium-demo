package com.testmonitor.beaker.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

public class LogoutFlowTest extends BaseTest {

    @Test(description = "Logs out and returns to landing page")
    public void testLogout() {
        landingPage.navigateToLandingPage();
        loginPage = landingPage.clickGetStarted();
        taskPage = loginPage.loginWithCredentials("test@example.com", "password123");

        landingPage = taskPage.clickLogout();
        Assert.assertTrue(landingPage.isWelcomeHeadingVisible(),
            "Welcome heading should be visible after logout");
        String headingText = landingPage.getWelcomeHeadingText();
        Assert.assertTrue(headingText.contains("Welcome to Beaker"),
            "Should show welcome message after logout");
    }
}
