package com.parabank.pages;

import com.parabank.utilities.WaitUtility;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

public class TransferFundsPage {

    private final WebDriver driver;
    private final WaitUtility waitUtility;

    private final By amountField = By.id("amount");
    private final By fromAccountDropdown = By.id("fromAccountId");
    private final By toAccountDropdown = By.id("toAccountId");
    private final By transferButton = By.cssSelector("input[value='Transfer']");
    private final By confirmationTitle = By.cssSelector("#showResult h1");
    private final By errorMessage = By.cssSelector("div.error, span.error");

    public TransferFundsPage(WebDriver driver) {
        this.driver = driver;
        this.waitUtility = new WaitUtility(driver);
    }

    public void enterAmount(String amount) {
        waitUtility.waitForVisibility(amountField).sendKeys(amount);
    }

    public void selectFromAccount(String accountId) {
        new Select(driver.findElement(fromAccountDropdown)).selectByVisibleText(accountId);
    }

    public void selectToAccount(String accountId) {
        new Select(driver.findElement(toAccountDropdown)).selectByVisibleText(accountId);
    }

    public void clickTransfer() {
        driver.findElement(transferButton).click();
    }

    public void transferFunds(String amount, String fromAccountId, String toAccountId) {
        enterAmount(amount);
        selectFromAccount(fromAccountId);
        selectToAccount(toAccountId);
        clickTransfer();
    }

    public boolean isTransferSuccessful() {
        try {
            return waitUtility.waitForVisibility(confirmationTitle).getText().contains("Transfer Complete");
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isErrorDisplayed() {
        try {
            return waitUtility.waitForVisibility(errorMessage).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
}
