package com.testmonitor.beaker.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

public class LandingPageTest extends BaseTest {

    @Test(description = "Displays landing page with correct title")
    public void testLandingPageTitle() {
        landingPage.navigateToLandingPage();
        String pageTitle = landingPage.getPageTitle();
        Assert.assertTrue(pageTitle.contains("Beaker"), "Page title should contain 'Beaker'");
        Assert.assertTrue(landingPage.isWelcomeHeadingVisible(), "Welcome heading should be visible");
    }

    @Test(description = "Navigates to login page when clicking Get Started")
    public void testNavigateToLogin() {
        landingPage.navigateToLandingPage();
        loginPage = landingPage.clickGetStarted();
        String headingText = loginPage.getSignInHeadingText();
        Assert.assertTrue(headingText.contains("Sign In"), "Heading should contain 'Sign In'");
    }

    @Test(description = "Displays TestMonitor attribution link")
    public void testTestMonitorLink() {
        landingPage.navigateToLandingPage();
        Assert.assertTrue(landingPage.isTestMonitorLinkVisible(), "TestMonitor link should be visible");
        Assert.assertEquals(landingPage.getTestMonitorLinkText(), "TestMonitor",
            "Link text should be 'TestMonitor'");
    }
}
