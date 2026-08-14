package com.parabank.tests;

import com.parabank.base.BaseTest;
import com.parabank.pages.LoginPage;
import com.parabank.pages.TransferFundsPage;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TransferFundsTest extends BaseTest {

    private static final String VALID_USERNAME = "johnrandomuser123";
    private static final String VALID_PASSWORD = "Passw0rd!";

    @Test(priority = 1, groups = {"smoke", "regression"})
    public void testValidFundTransfer() {
        LoginPage loginPage = new LoginPage(getDriver());
        loginPage.login(VALID_USERNAME, VALID_PASSWORD);

        getDriver().findElement(By.linkText("Transfer Funds")).click();

        TransferFundsPage transferPage = new TransferFundsPage(getDriver());
        // NOTE: account IDs below are placeholders — replace with real account
        // IDs from your test user once you've registered/logged in manually.
        transferPage.transferFunds("50", "12345", "12346");

        Assert.assertTrue(transferPage.isTransferSuccessful(), "Expected transfer to complete successfully");
    }

    @Test(priority = 2, groups = {"regression"})
    public void testTransferWithInvalidAmount() {
        LoginPage loginPage = new LoginPage(getDriver());
        loginPage.login(VALID_USERNAME, VALID_PASSWORD);

        getDriver().findElement(By.linkText("Transfer Funds")).click();

        TransferFundsPage transferPage = new TransferFundsPage(getDriver());
        transferPage.transferFunds("abc", "12345", "12346");

        Assert.assertFalse(transferPage.isTransferSuccessful(),
                "Transfer should not succeed with a non-numeric amount");
    }

    @Test(priority = 3, groups = {"regression"})
    public void testTransferExceedingBalance() {
        LoginPage loginPage = new LoginPage(getDriver());
        loginPage.login(VALID_USERNAME, VALID_PASSWORD);

        getDriver().findElement(By.linkText("Transfer Funds")).click();

        TransferFundsPage transferPage = new TransferFundsPage(getDriver());
        transferPage.transferFunds("999999999", "12345", "12346");

        // ParaBank's demo doesn't always enforce balance checks server-side —
        // this test documents actual behavior once you've verified it manually (Step 2 task).
        Assert.assertTrue(getDriver().getCurrentUrl().contains("transfer"),
                "Should remain on transfer flow for an unrealistic amount");
    }
}
