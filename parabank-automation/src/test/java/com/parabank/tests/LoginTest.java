package com.parabank.tests;

import com.parabank.base.BaseTest;
import com.parabank.pages.LoginPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginTest extends BaseTest {

    // NOTE: replace with a username/password you registered via RegistrationTest
    // or manually on parabank.parasoft.com before running this class.
    private static final String VALID_USERNAME = "johnrandomuser123";
    private static final String VALID_PASSWORD = "Passw0rd!";

    @Test(priority = 1, groups = {"smoke", "regression"})
    public void testValidLogin() {
        LoginPage loginPage = new LoginPage(getDriver());
        loginPage.login(VALID_USERNAME, VALID_PASSWORD);

        Assert.assertTrue(getDriver().getCurrentUrl().contains("overview.htm"),
                "Expected to land on accounts overview after valid login");
    }

    @Test(priority = 2, groups = {"regression"})
    public void testInvalidUsername() {
        LoginPage loginPage = new LoginPage(getDriver());
        loginPage.login("nonexistent_user_xyz", VALID_PASSWORD);

        Assert.assertTrue(loginPage.isErrorDisplayed(), "Expected an error for invalid username");
    }

    @Test(priority = 3, groups = {"regression"})
    public void testInvalidPassword() {
        LoginPage loginPage = new LoginPage(getDriver());
        loginPage.login(VALID_USERNAME, "wrongPassword123");

        Assert.assertTrue(loginPage.isErrorDisplayed(), "Expected an error for invalid password");
    }

    @Test(priority = 4, groups = {"sanity", "regression"})
    public void testEmptyCredentials() {
        LoginPage loginPage = new LoginPage(getDriver());
        loginPage.login("", "");

        Assert.assertTrue(loginPage.isErrorDisplayed(), "Expected an error for empty credentials");
    }
}
