package com.parabank.tests;

import com.parabank.base.BaseTest;
import com.parabank.pages.FindTransactionsPage;
import com.parabank.pages.LoginPage;
import org.openqa.selenium.By;
import org.testng.annotations.Test;

public class FindTransactionsTest extends BaseTest {

    private static final String VALID_USERNAME = "johnrandomuser123";
    private static final String VALID_PASSWORD = "Passw0rd!";

    @Test(priority = 1, groups = {"sanity", "regression"})
    public void testFindTransactionsPageLoads() {
        LoginPage loginPage = new LoginPage(getDriver());
        loginPage.login(VALID_USERNAME, VALID_PASSWORD);

        getDriver().findElement(By.linkText("Find Transactions")).click();

        // Presence of the page itself is the assertion here; searching by a
        // specific known transaction ID depends on data seeded per test run,
        // which you can extend once you have a stable test account.
        org.testng.Assert.assertTrue(getDriver().getCurrentUrl().contains("findtrans"),
                "Expected to land on the Find Transactions page");
    }
}
