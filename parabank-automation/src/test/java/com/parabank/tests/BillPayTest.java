package com.parabank.tests;

import com.parabank.base.BaseTest;
import com.parabank.pages.BillPayPage;
import com.parabank.pages.LoginPage;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

public class BillPayTest extends BaseTest {

    private static final String VALID_USERNAME = "johnrandomuser123";
    private static final String VALID_PASSWORD = "Passw0rd!";

    @Test(priority = 1, groups = {"smoke", "regression"})
    public void testValidBillPayment() {
        LoginPage loginPage = new LoginPage(getDriver());
        loginPage.login(VALID_USERNAME, VALID_PASSWORD);

        getDriver().findElement(By.linkText("Bill Pay")).click();

        BillPayPage billPayPage = new BillPayPage(getDriver());
        billPayPage.fillBillPayForm("Electric Company", "456 Power Ave", "Springfield",
                "IL", "62704", "5555559876", "12345", "75");
        billPayPage.clickSendPayment();

        Assert.assertTrue(billPayPage.isPaymentSuccessful(), "Expected bill payment to complete successfully");
    }

    @Test(priority = 2, groups = {"regression"})
    public void testBillPaymentWithMissingPayeeName() {
        LoginPage loginPage = new LoginPage(getDriver());
        loginPage.login(VALID_USERNAME, VALID_PASSWORD);

        getDriver().findElement(By.linkText("Bill Pay")).click();

        BillPayPage billPayPage = new BillPayPage(getDriver());
        billPayPage.fillBillPayForm("", "456 Power Ave", "Springfield",
                "IL", "62704", "5555559876", "12345", "75");
        billPayPage.clickSendPayment();

        Assert.assertFalse(billPayPage.isPaymentSuccessful(),
                "Payment should not succeed without a payee name");
    }
}
