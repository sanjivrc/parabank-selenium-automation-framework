package com.parabank.tests;

import com.parabank.base.BaseTest;
import com.parabank.pages.LoginPage;
import com.parabank.pages.OpenNewAccountPage;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

public class OpenNewAccountTest extends BaseTest {

    private static final String VALID_USERNAME = "johnrandomuser123";
    private static final String VALID_PASSWORD = "Passw0rd!";

    @Test(priority = 1, groups = {"regression"})
    public void testOpenNewSavingsAccount() {
        LoginPage loginPage = new LoginPage(getDriver());
        loginPage.login(VALID_USERNAME, VALID_PASSWORD);

        getDriver().findElement(By.linkText("Open New Account")).click();

        OpenNewAccountPage openAccountPage = new OpenNewAccountPage(getDriver());
        openAccountPage.selectAccountType("SAVINGS");
        openAccountPage.clickOpenNewAccount();

        Assert.assertTrue(openAccountPage.getSuccessTitle().contains("Account Opened"),
                "Expected confirmation that the new account was opened");
        Assert.assertNotNull(openAccountPage.getNewAccountId(), "Expected a new account ID to be generated");
    }
}
