package com.parabank.tests;

import com.parabank.base.BaseTest;
import com.parabank.pages.AccountsOverviewPage;
import com.parabank.pages.LoginPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class AccountsOverviewTest extends BaseTest {

    private static final String VALID_USERNAME = "johnrandomuser123";
    private static final String VALID_PASSWORD = "Passw0rd!";

    @Test(priority = 1, groups = {"smoke", "regression"}, dependsOnGroups = {})
    public void testAccountsOverviewLoadsAfterLogin() {
        LoginPage loginPage = new LoginPage(getDriver());
        loginPage.login(VALID_USERNAME, VALID_PASSWORD);

        AccountsOverviewPage overviewPage = new AccountsOverviewPage(getDriver());
        Assert.assertTrue(overviewPage.isOverviewPageDisplayed(), "Accounts overview page did not load");
    }

    @Test(priority = 2, groups = {"sanity", "regression"})
    public void testAtLeastOneAccountIsDisplayed() {
        LoginPage loginPage = new LoginPage(getDriver());
        loginPage.login(VALID_USERNAME, VALID_PASSWORD);

        AccountsOverviewPage overviewPage = new AccountsOverviewPage(getDriver());
        Assert.assertTrue(overviewPage.getAccountCount() > 0, "Expected at least one account to be listed");
    }
}
