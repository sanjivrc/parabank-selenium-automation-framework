package com.parabank.pages;

import com.parabank.utilities.WaitUtility;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

public class BillPayPage {

    private final WebDriver driver;
    private final WaitUtility waitUtility;

    private final By payeeName = By.name("payee.name");
    private final By payeeAddress = By.name("payee.address.street");
    private final By payeeCity = By.name("payee.address.city");
    private final By payeeState = By.name("payee.address.state");
    private final By payeeZip = By.name("payee.address.zipCode");
    private final By payeePhone = By.name("payee.phoneNumber");
    private final By payeeAccount = By.name("payee.accountNumber");
    private final By verifyAccount = By.name("verifyAccount");
    private final By amount = By.name("amount");
    private final By fromAccountDropdown = By.name("fromAccountId");
    private final By sendPaymentButton = By.cssSelector("input[value='Send Payment']");
    private final By confirmationTitle = By.cssSelector("#billpayResult h1");

    public BillPayPage(WebDriver driver) {
        this.driver = driver;
        this.waitUtility = new WaitUtility(driver);
    }

    public void fillBillPayForm(String name, String address, String city, String state, String zip,
                                 String phone, String accountNumber, String amountVal) {
        driver.findElement(payeeName).sendKeys(name);
        driver.findElement(payeeAddress).sendKeys(address);
        driver.findElement(payeeCity).sendKeys(city);
        driver.findElement(payeeState).sendKeys(state);
        driver.findElement(payeeZip).sendKeys(zip);
        driver.findElement(payeePhone).sendKeys(phone);
        driver.findElement(payeeAccount).sendKeys(accountNumber);
        driver.findElement(verifyAccount).sendKeys(accountNumber);
        driver.findElement(amount).sendKeys(amountVal);
    }

    public void selectFromAccount(String accountId) {
        new Select(driver.findElement(fromAccountDropdown)).selectByVisibleText(accountId);
    }

    public void clickSendPayment() {
        driver.findElement(sendPaymentButton).click();
    }

    public boolean isPaymentSuccessful() {
        try {
            return waitUtility.waitForVisibility(confirmationTitle).getText().contains("Bill Payment Complete");
        } catch (Exception e) {
            return false;
        }
    }
}
