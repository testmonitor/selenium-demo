package com.testmonitor.beaker.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class PasswordResetTest extends BaseTest {

    @BeforeMethod
    public void navigateToPasswordResetPage() {
        landingPage.navigateToLandingPage();
        loginPage = landingPage.clickGetStarted();
        passwordResetPage = loginPage.clickForgotPassword();
    }

    @Test(description = "Successfully sends password reset link - EXPECTED TO FAIL due to bug")
    public void testPasswordResetSuccess() {
        passwordResetPage.fillResetEmail("test@example.com");
        passwordResetPage.clickSendResetLink();
        Assert.assertTrue(passwordResetPage.isResetErrorNotVisible(),
            "Reset error should not be visible on success");
    }

    @Test(description = "Shows error message when reset fails - EXPECTED TO PASS due to bug")
    public void testPasswordResetError() {
        passwordResetPage.fillResetEmail("test@example.com");
        passwordResetPage.clickSendResetLink();
        Assert.assertTrue(passwordResetPage.isResetErrorVisible(),
            "Reset error should be visible");
        Assert.assertTrue(passwordResetPage.getResetErrorText().contains("Unable to process your request"),
            "Error message should contain correct text");
    }
}
